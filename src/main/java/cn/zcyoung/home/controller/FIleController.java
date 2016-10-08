package cn.zcyoung.home.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.zcyoung.home.pojo.Ufile;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.UfileService;
import cn.zcyoung.home.utils.GoForward;
import cn.zcyoung.home.utils.PjaxUtils;
import cn.zcyoung.home.web.auth.AuthPassport;

@Controller
@RequestMapping("/file")
public class FIleController {

	@Resource
	private PageService pageService;
	@Resource
	private UfileService ufileService;

	@RequestMapping("")
	public String file1(HttpServletRequest request){
		request.setAttribute("Page", pageService.GetUfilePage(1, 10, false));
		return PjaxUtils.get(request, "file");
	}
	@RequestMapping("/{page}")
	public String file1(@PathVariable Integer page, HttpServletRequest request){
		request.setAttribute("Page", pageService.GetUfilePage(page, 10, false));
		return PjaxUtils.get(request, "file");
	}

	@AuthPassport(isuser = true)
	@RequestMapping("/my")
	public String file(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("Page", pageService.GetUfilePage(1, 10, true, user.getId()));
		return PjaxUtils.get(request, "myfile");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/my/{page}")
	public String file(@PathVariable Integer page, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("Page", pageService.GetUfilePage(page, 10, true, user.getId()));
		return PjaxUtils.get(request, "myfile");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/delete/{id}")
	public String delete(HttpServletRequest request, @PathVariable Integer id){
		Ufile ufile = ufileService.GetUfileById(id);
		User user = (User) request.getSession().getAttribute("User");
		if(ufile == null){
			request.setAttribute("msg", "文件不存在");
		}else{
			if(user.getId() != ufile.getUserId()){
				request.setAttribute("GoForward", new GoForward("这不是你的文件，你没有权限操作，3s后跳转", "3000", "/file/my"));
				return "goforward";
			}else{
				@SuppressWarnings("deprecation")
				String path = request.getRealPath("/") + ufile.getAddress();
				File file = new File(path);
				if(file.delete()) {
					ufileService.DeleteUfile(id);
					request.setAttribute("msg", "删除成功。");
				}
				else request.setAttribute("msg", "删除失败。");
			}
		}
		request.setAttribute("Page", pageService.GetUfilePage(1, 10, true, user.getId()));
		return PjaxUtils.get(request, "myfile");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/down/{id}")
	public void down(HttpServletRequest request,HttpServletResponse response,@PathVariable Integer id){
		Ufile ufile = ufileService.GetUfileById(id);
		User user = (User) request.getSession().getAttribute("User");
		if(ufile.getPri() == 1 && user.getId() != ufile.getUserId()){
			try{response.sendRedirect("/view/login");}catch(Exception e){}
			return;
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			@SuppressWarnings("deprecation")
			String path=request.getRealPath(ufile.getAddress());
			response.setContentType("application/octet-stream");
			response.setContentLength(ufile.getSize().intValue());  
			response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(ufile.getName(),"UTF-8"));
			in=new FileInputStream(new File(path));
			out=response.getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while((len=in.read(b))!=-1){
				out.write(b,0,len);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		finally{
			try {
				if(out!=null)out.close();
				if(in!=null)in.close();
			} catch (IOException e1) {
				//e1.printStackTrace();
			}

		}
	}

	@AuthPassport(isuser = true)
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request){
		return PjaxUtils.get(request, "uploadfile");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/upload/now")
	public String uploadnow(HttpServletRequest request, String pri){
		User user=(User) request.getSession().getAttribute("User");
		MultipartHttpServletRequest multipartrequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile obj = (CommonsMultipartFile) multipartrequest.getFile("uf");
		if(obj==null||obj.isEmpty()||obj.getOriginalFilename().endsWith("jsp")){
			request.setAttribute("msg", "内部错误");
			return "uploadfile";
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String floder = df.format(new Date());
		@SuppressWarnings("deprecation")
		String address=request.getRealPath("/upload/file/"+floder+"/");
		File file=new File(address);
		if(!file.exists()){
			file.mkdirs();
		}
		String af=new Date().getTime() + "_" + (int)(Math.random()*10000000) +obj.getOriginalFilename().substring(obj.getOriginalFilename().lastIndexOf('.'));

		Ufile uf=new Ufile();
		uf.setTime(new Date());
		uf.setName(obj.getOriginalFilename());
		uf.setSize(obj.getSize());
		uf.setType(obj.getContentType());
		uf.setUserId(user.getId());
		uf.setAddress("/upload/file/"+floder+"/"+af);
		if(pri==null||pri.equals("")) uf.setPri(0);
		else uf.setPri(1);

		try{
			File file2 = new File(address+"/"+af);
			byte[] bytes =  obj.getBytes();
			FileOutputStream fos = new FileOutputStream(file2);
			fos.write(bytes);
			fos.flush();
			fos.close();

			if(ufileService.AddUfile(uf)){
				request.setAttribute("msg", "上传成功！！可以前往附件管理查看。");
				return "uploadfile";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("msg", "上传失败，请检查文件大小后，重试。。。。");
		return "uploadfile";
	}

	@SuppressWarnings({ "unchecked" })
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping(value="/upload_json",  produces = "text/html;charset=UTF-8")
	public String upload_json(HttpServletRequest request){
		//文件保存目录路径
		@SuppressWarnings("deprecation")
		String savePath = request.getRealPath("/") + "upload/";
		//文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media",
				"swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file",
				"doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,jar");
		//最大文件大小
		long maxSize = 5000000;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		//检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。" + savePath);
		}
		//检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确。");
		}
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		MultipartHttpServletRequest multipartrequest = (MultipartHttpServletRequest) request;
		
		try {
			@SuppressWarnings("rawtypes")
			Iterator itr = multipartrequest.getFileNames();
			while (itr.hasNext()) {
				//FileItem item = (FileItem) itr.next();
				String name = (String) itr.next();
				MultipartFile mul = multipartrequest.getFile(name);
				String fileName = mul.getOriginalFilename();
				long fileSize = mul.getSize();
					//检查文件大小
					if (mul.getSize() > maxSize) {
						return getError("上传文件大小超过限制。");
					}
					//检查扩展名
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.<String> asList(extMap.get(dirName).split(","))
							.contains(fileExt)) {
						return getError("上传文件扩展名是不允许的扩展名。\n只允许"
								+ extMap.get(dirName) + "格式。");
					}

					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = df.format(new Date()) + "_"
							+ new Random().nextInt(1000) + "." + fileExt;
					Ufile uf;
					try {
						File uploadedFile = new File(savePath, newFileName);
						byte[] bytes =  mul.getBytes();
						FileOutputStream fos = new FileOutputStream(uploadedFile);
						fos.write(bytes);
						fos.flush();
						fos.close();
						User user = (User) request.getSession().getAttribute(
								"User");

						uf = new Ufile();
						uf.setTime(new Date());
						uf.setName(fileName);
						uf.setSize(fileSize);
						uf.setType(fileExt);
						uf.setUserId(user.getId());
						uf.setAddress(saveUrl + newFileName);
						uf.setPri(0);	

						if(!ufileService.AddUfile(uf)) {return getError("上传文件失败。");}
						uf = ufileService.GetUfileByAddress(uf.getAddress());
					} catch (Exception e) {
						e.printStackTrace();
						return getError("上传文件失败。");
					}

					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					if (fileExt.contains("gif") 
							|| fileExt.contains("jpg")
							|| fileExt.contains("jpeg")
							|| fileExt.contains("png")
							|| fileExt.contains("bmp"))
						obj.put("url", saveUrl + newFileName);
					else
						obj.put("url", "/file/down/"+ uf.getId());
					return obj.toJSONString();
				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return getError("未知错误");
	}

	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

}
