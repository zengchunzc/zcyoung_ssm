package cn.zcyoung.home.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.pojo.Log;
import cn.zcyoung.home.pojo.LoginHistory;
import cn.zcyoung.home.pojo.Message;
import cn.zcyoung.home.pojo.Ufile;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.FriendUrlService;
import cn.zcyoung.home.service.LogService;
import cn.zcyoung.home.service.LoginHistoryService;
import cn.zcyoung.home.service.MessageService;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.UfileService;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.utils.HtmlUtils;
import cn.zcyoung.home.utils.IPUtil;
import cn.zcyoung.home.utils.MailUtils;
import cn.zcyoung.home.utils.PjaxUtils;
import cn.zcyoung.home.utils.mail.SimpleMailSender;
import cn.zcyoung.home.web.auth.AuthPassport;
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private LoginHistoryService loginHistoryService;
	@Resource
	private MailUtils mailUtils;
	@Resource
	private MessageService messageService;
	@Resource
	private LogService logService;
	@Resource
	private UfileService ufileService;
	@Resource
	private FriendUrlService friendUrlService;
	@Resource
	private PageService pageService;
	
	
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping(value = "/addfu", produces = "text/html;charset=UTF-8")
	public String addfu(String name, String url, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		if(name != null && url != null && !name.equals("") && !url.equals("")){
			Friendurl fu = new Friendurl();
			fu.setTime(new Date());
			fu.setName(name);
			fu.setCheckState(0);
			fu.setUrl(url);
			fu.setUserId(user.getId());
			fu.setClick(0);
			
			Message message = new Message();
			message.setData("您有一条来自" + user.getNickname() + "的友链申请，请前往处理。");
			message.setFromId(1);
			message.setTime(new Date());
			message.setState(0);
			
			List<User> listadmin = userService.GetListAdmin();
			if(listadmin != null)
			for(int i = 0; i < listadmin.size(); i++){
				message.setToId(listadmin.get(i).getId());
				messageService.AddMessage(message);
			}
			
			if(friendUrlService.AddFriendUrl(fu)) return "ok";
		}
		return "error";
	}
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping(value = "/modifypassword", produces = "text/html;charset=UTF-8")
	public String modifypassword(HttpServletRequest request, String password, String newpassword){
		User user = (User) request.getSession().getAttribute("User");
		user = userService.GetUserById(user.getId());
		if(password.equals(user.getPassword())){
			user.setPassword(newpassword);
			if(userService.UpdateUser(user)) {
				Log log = new Log();
				log.setUserId(user.getId());
				log.setData("重置了密码");
				log.setType("user");
				log.setTime(new Date());
				logService.AddLog(log);
				return "ok";
			}
			else return "error";
		}
		return "原密码错误。";
	}
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping(value = "/addfriend/{username}", produces = "text/html;charset=UTF-8")
	public String addfriend(@PathVariable String username, HttpServletRequest request){
		try {
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = (User) request.getSession().getAttribute("User");
		User fuser = userService.GetUserByUsername(username);
		if(fuser == null) return "添加失败，用户不存在。";
		else if(userService.AddFriend(user, fuser)) {
			Message message = new Message();
			message.setData(user.getUsername() + "申请添加你为好友.");
			message.setFromId(1);
			message.setToId(fuser.getId());
			message.setState(0);
			message.setTime(new Date());
			message.setUrl("/view/myfriend");
			messageService.AddMessage(message);
			return "ok";
		}
		return "error";
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/checkfriend/{username}")
	public String checkfriend(@PathVariable String username, HttpServletRequest request){
		try {
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = (User) request.getSession().getAttribute("User");
		User fuser = userService.GetUserByUsername(username);
		if(userService.CheckFriend(user, fuser)){
			Message message = new Message();
			message.setData(user.getUsername() + "同意了你的好友请求.");
			message.setFromId(1);
			message.setToId(fuser.getId());
			message.setState(0);
			message.setTime(new Date());
			message.setUrl("/view/myfriend");
			messageService.AddMessage(message);
			request.setAttribute("msg", "审核成功!");
		}
		else request.setAttribute("msg", "审核失败!");
		request.setAttribute("Page", pageService.GetFriendUserPage(1, 10, user.getId()));
		request.setAttribute("CPage", pageService.GetTmpFriendUserPage(user.getId()));
		return PjaxUtils.get(request, "myfriend");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping(value = "/deletefriend/{username}")
	public String deletefriend(@PathVariable String username, HttpServletRequest request){
		try {
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = (User) request.getSession().getAttribute("User");
		User fuser = userService.GetUserByUsername(username);

		if(fuser != null) {
			userService.DeleteFriendId(fuser, user.getId());
			userService.DeleteFriendId(user, fuser.getId());
			Message message = new Message();
			message.setData(user.getUsername() + "解除了你们的好友关系.");
			message.setFromId(1);
			message.setToId(fuser.getId());
			message.setState(0);
			message.setTime(new Date());
			messageService.AddMessage(message);
			request.setAttribute("msg", "删除成功!");
		}else
			request.setAttribute("msg", "删除失败!");
		request.setAttribute("Page", pageService.GetFriendUserPage(1, 10, user.getId()));
		request.setAttribute("CPage", pageService.GetTmpFriendUserPage(user.getId()));
		return PjaxUtils.get(request, "myfriend");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping(value = "/deletetfriend/{username}")
	public String deletetfriend(@PathVariable String username, HttpServletRequest request){
		try {
			username = new String(username.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User user = (User) request.getSession().getAttribute("User");
		User fuser = userService.GetUserByUsername(username);
		if(fuser != null) {
			userService.DeleteTmpFriendId(user, fuser.getId());
			request.setAttribute("msg", "拒绝成功!");
		}else request.setAttribute("msg", "拒绝失败!");
		request.setAttribute("Page", pageService.GetFriendUserPage(1, 10, user.getId()));
		request.setAttribute("CPage", pageService.GetTmpFriendUserPage(user.getId()));
		return PjaxUtils.get(request, "myfriend");
	}
	
	@ResponseBody
	@RequestMapping(value="/AjaxRegist", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String AjaxRegist(User user, HttpServletRequest request){
		if(user.getEmail()==null||user.getEmail().equals("")||user.getNickname()==null||user.getNickname().equals("")||user.getPassword()==null||user.getPassword().equals("")){
			return "error:不能有为空项。";
		}
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";    
		Pattern regex = Pattern.compile(check);    
		Matcher matcher = regex.matcher(user.getEmail());    
		boolean isMatched = matcher.matches();
		if(!isMatched) return "error:邮件格式错误。";
		user.setrIp(IPUtil.getIpAddress(request));
		user.setPreTime(new Date());
		user.setPreIp(IPUtil.getIpAddress(request));
		user.setRole("user");
		user.setEmailYz(0);
		user.setFriendList("1;");
		user.setPic("/upload/nopic.jpg");
		user.setrTime(new Date());
		user.setUsername(HtmlUtils.delHTMLTag(user.getUsername()));
		user.setNickname(HtmlUtils.delHTMLTag(user.getNickname()));
		user.setSignature(HtmlUtils.delHTMLTag(user.getSignature()));
		if(userService.AddUser(user)){
			user = userService.GetUserByUsername(user.getUsername());
			mailUtils.SendMailCode(user);
			return "ok:注册成功,快登陆吧";
		}
		return "error:注册失败。可能用户名已被使用。";
	}
	
	@ResponseBody
	@RequestMapping(value="/AjaxLogin", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String AjaxLogin(String fromUrl,String username,String password,HttpSession session,HttpServletRequest request){
		if(username==null||username.equals("")||password==null||password.equals("")){
			return "error:不能有为空项。";
		}
		
		User myuser=new User();
		myuser.setUsername(username);
		myuser.setPassword(password);
		User user = userService.Login(username, password);
		if(user!=null){
			
			user.setPreIp(IPUtil.getIpAddress(request));
			user.setPreTime(new Date());
			userService.UpdateUser(user);
			
			LoginHistory lh = new LoginHistory();
			lh.setUserId(user.getId());
			lh.setlIp(IPUtil.getIpAddress(request));
			lh.setlTime(new Date());
			lh.setUserAgent(request.getHeader("User-agent"));
			lh.setHostname(request.getRemoteHost());
			loginHistoryService.AddLoginHistory(lh);
			String rem = request.getParameter("rem");
			if(rem != null && rem.equals("ok")) session.setMaxInactiveInterval(60*60*24*7);
			session.setAttribute("User", user);
			if(fromUrl != null && !fromUrl.equals("")){}
			else fromUrl = "/view/home";
			return "ok:" + fromUrl;
		}
		else{
			return "error:用户名或密码错误。";
		}
	}
	
	@AuthPassport(isuser=true)
	@RequestMapping("/modify")
	public String updatemodify(String nickname,String signature,Model model,HttpSession session,HttpServletRequest request){
		if(nickname==null||nickname.equals("")||signature==null||signature.equals("")){
			model.addAttribute("msg", "不能为空！");
			return "modify";
		}

		User user=(User) session.getAttribute("User");
		user = userService.GetUserById(user.getId());
		user.setNickname(HtmlUtils.delHTMLTag(nickname));
		user.setSignature(HtmlUtils.delHTMLTag(signature));

		MultipartHttpServletRequest multipartrequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile obj = (CommonsMultipartFile) multipartrequest.getFile("pic");
		if(obj.getOriginalFilename().equals("")){
			if(userService.UpdateUser(user)){
				request.setAttribute("User", userService.GetUserById(user.getId()));
				request.setAttribute("msg", "修改信息成功");
			}else 
				request.setAttribute("msg", "修改失败。");
			return "modify";
		}
		
		if(obj.getOriginalFilename().endsWith(".gif")||
				obj.getOriginalFilename().endsWith(".jpg")||
				obj.getOriginalFilename().endsWith(".jpeg")||
				obj.getOriginalFilename().endsWith(".png")||
				obj.getOriginalFilename().endsWith(".bmp")
				){

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String floder = df.format(new Date());
			@SuppressWarnings("deprecation")
			String address=request.getRealPath("/upload/file/"+floder+"/");
			File file=new File(address);
			if(!file.exists()){
				file.mkdirs();
			}
			String af=new Date().getTime()+obj.getOriginalFilename().substring(obj.getOriginalFilename().lastIndexOf('.'));

			Ufile uf=new Ufile();
			uf.setName(obj.getOriginalFilename());
			uf.setSize(obj.getSize());
			uf.setType(obj.getContentType());
			uf.setUserId(user.getId());
			uf.setTime(new Date());
			uf.setPri(1);
			uf.setAddress("/upload/file/"+floder+"/"+af);

			try{
				File file2 = new File(address+"/"+af);
				byte[] bytes =  obj.getBytes();
				FileOutputStream fos = new FileOutputStream(file2);
				fos.write(bytes);
				fos.flush();
				fos.close();

				user.setPic(uf.getAddress());

				if(ufileService.AddUfile(uf)&&userService.UpdateUser(user)){
					request.setAttribute("msg", "修改信息成功");
					request.setAttribute("User", userService.GetUserById(user.getId()));
					return PjaxUtils.get(request, "modify");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		model.addAttribute("msg", "修改失败,文件大小50m以内，且只能为图片。");
		return PjaxUtils.get(request, "modify");
	}
	
	@ResponseBody
	@RequestMapping(value="/AjaxSendMailYz", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String AjaxSendMailYz(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		if(user != null && user.getEmailYz() != 1){
			user.setEmailYz(1);
			userService.UpdateUser(user);
			SimpleMailSender.SendMail(user.getEmail(),"邮件系统修复中...已默认为您认证邮箱。", "来自zcyoung");
			return "ok";
		}
		return "error";
	}
	
	
	
}
