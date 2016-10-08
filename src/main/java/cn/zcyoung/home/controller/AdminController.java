package cn.zcyoung.home.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.pojo.Log;
import cn.zcyoung.home.pojo.Message;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.FriendUrlService;
import cn.zcyoung.home.service.LogService;
import cn.zcyoung.home.service.MessageService;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.utils.MD5Utils;
import cn.zcyoung.home.utils.MailUtils;
import cn.zcyoung.home.utils.PjaxUtils;
import cn.zcyoung.home.web.auth.AuthPassport;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	private UserService userService;
	@Resource
	private FriendUrlService friendUrlService;
	@Resource
	private PageService pageService;
	@Resource
	private LogService logService;
	@Resource
	private MessageService messageService;
	
	@AuthPassport(isadmin = true)
	@RequestMapping("/user")
	public String user(HttpServletRequest request){
		String key = request.getParameter("key");
		if(key == null || key.equals(""))
			request.setAttribute("Page", pageService.GetUserPage(1, 10));
		else {
			request.setAttribute("Page", pageService.GetUserPage(1, 10, key));
			request.setAttribute("key", key);
		}
		return PjaxUtils.get(request, "admin_user");
	}
	@AuthPassport(isadmin = true)
	@RequestMapping("/user/{page}")
	public String user(HttpServletRequest request, @PathVariable Integer page){
		request.setAttribute("Page", pageService.GetUserPage(page, 10));
		return PjaxUtils.get(request, "admin_user");
	}
	@AuthPassport(isadmin = true)
	@RequestMapping("/user/{page}/{key}")
	public String user(HttpServletRequest request, @PathVariable String key, @PathVariable Integer page){
		try {
			key = new String(key.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("Key", key);
		request.setAttribute("Page", pageService.GetUserPage(page, 10, key));
		return PjaxUtils.get(request, "admin_user");
	}
	@AuthPassport(isadmin = true)
	@RequestMapping("/friendurl")
	public String friendurl(HttpServletRequest request){
		request.setAttribute("Page", pageService.GetFriendUrlPage(FriendUrlService.By_TIME, 1, 10, true));
		return PjaxUtils.get(request, "admin_friendurl");
	}
	@AuthPassport(isadmin = true)
	@RequestMapping("/friendurl/{page}")
	public String friendurl(HttpServletRequest request, @PathVariable Integer page){
		request.setAttribute("Page", pageService.GetFriendUrlPage(FriendUrlService.By_TIME, page, 10, true));
		return PjaxUtils.get(request, "admin_friendurl");
	}
	
	@AuthPassport(isadmin = true)
	@RequestMapping(value="/checkfriendurl/{id}")
	public String cfriendurl(HttpServletRequest request, @PathVariable Integer id){
		User user = (User) request.getSession().getAttribute("User");
		Friendurl fu = friendUrlService.GetFriendUrlById(id);
		if(fu != null && fu.getCheckState() != 1){
			if(friendUrlService.CheckFreind(user, id)){
				Log log = new Log();
				log.setData(user.getUsername() + "通过了" + fu.getUserId() + "的[" + fu.getName() + "]友链.");
				log.setTime(new Date());
				log.setType("admin");
				log.setUserId(user.getId());
				logService.AddLog(log);
				Message message = new Message();
				message.setTime(new Date());
				message.setFromId(1);
				message.setToId(fu.getUserId());
				message.setState(0);
				message.setUrl("/view/friendurl");
				message.setData("亲的[" + fu.getName() + "]已通过审核,谢谢您的使用.");
				messageService.AddMessage(message);
				request.setAttribute("msg", "审核通过。");
				request.setAttribute("Page", pageService.GetFriendUrlPage(FriendUrlService.By_TIME, 1, 10, true));
				return PjaxUtils.get(request, "admin_friendurl");
			}
		}
		request.setAttribute("msg", "审核失败。");
		request.setAttribute("Page", pageService.GetFriendUrlPage(FriendUrlService.By_TIME, 1, 10, true));
		return PjaxUtils.get(request, "admin_friendurl");
	}
	@AuthPassport(isadmin = true)
	@RequestMapping(value="/deletefriendurl/{id}")
	public String dfriendurl(HttpServletRequest request, @PathVariable Integer id){
		User user = (User) request.getSession().getAttribute("User");
		Friendurl fu = friendUrlService.GetFriendUrlById(id);
		if(fu != null && friendUrlService.DeleteFirendUrl(id)){
			Log log = new Log();
			log.setData(user.getUsername() + "删除了" + fu.getUserId() + "的[" + fu.getName() + "]友链.");
			log.setTime(new Date());
			log.setType("admin");
			log.setUserId(user.getId());
			logService.AddLog(log);
			request.setAttribute("msg", "删除成功。");
		}
		else request.setAttribute("msg", "删除失败。");
		
		request.setAttribute("Page", pageService.GetFriendUrlPage(FriendUrlService.By_TIME, 1, 10, true));
		return PjaxUtils.get(request, "admin_friendurl");
	}
	
	@AuthPassport(isadmin = true)
	@RequestMapping("/repassword/{id}")
	public String repassword(HttpServletRequest request, @PathVariable Integer id){
		User user = (User) request.getSession().getAttribute("User");
		User client = userService.GetUserById(id);
		if(client == null) request.setAttribute("msg", "重置密码失败.");
		else if(client.getRole().equals("admin") && user.getId() != 1) request.setAttribute("msg", "重置密码失败.");
		else {
			String pw = (int)(Math.random() * 100000000) + "";
			client.setPassword(MD5Utils.md5(pw));
			Log log = new Log();
			log.setData(user.getUsername() + "为" + client.getUsername() + "重置了密码.");
			log.setTime(new Date());
			log.setType("admin");
			log.setUserId(user.getId());
			logService.AddLog(log);
			
			log = new Log();
			log.setType("mail");
			log.setTime(new Date());
			log.setUserId(user.getId());
			log.setData("重置为了" + pw);
			logService.AddLog(log);
			
			client.setPassword(MD5Utils.md5(pw));
			userService.UpdateUser(client);
			
			Message message = new Message();
			message.setData("管理员为您重置了登录密码,请妥善保管.");
			message.setTime(new Date());
			message.setFromId(1);
			message.setToId(client.getId());
			message.setState(0);
			messageService.AddMessage(message);
			new MailUtils().SendResetPw(client, pw);
			request.setAttribute("msg", "重置成功,为【"+ pw +"】");
		}
		request.setAttribute("Page", pageService.GetUserPage(1, 10));
		return PjaxUtils.get(request, "admin_user"); 
	}
	
	
	
}
