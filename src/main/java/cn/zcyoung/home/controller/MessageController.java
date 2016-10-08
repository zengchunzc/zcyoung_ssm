package cn.zcyoung.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zcyoung.home.pojo.Message;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.MessageService;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.utils.DPage;
import cn.zcyoung.home.utils.PjaxUtils;
import cn.zcyoung.home.web.auth.AuthPassport;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Resource
	private PageService pageService;
	@Resource
	private MessageService messageService;
	@Resource
	private UserService userService;
	
	@AuthPassport(isuser = true)
	@RequestMapping("")
	public String message(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		DPage<Message> pa = pageService.GetMessagePage(1, 10, user.getId());
		Map<Integer, User> map = new HashMap<Integer, User>();
		if(pa != null)
		for(Message ms : pa.getDatas())
			map.put(ms.getFromId(), userService.GetUserById(ms.getFromId()));
		request.setAttribute("map", map);
		request.setAttribute("Page", pa);
		return PjaxUtils.get(request, "message");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/{page}")
	public String message(HttpServletRequest request, @PathVariable Integer page){
		User user = (User) request.getSession().getAttribute("User");
		DPage<Message> pa = pageService.GetMessagePage(page, 10, user.getId());
		Map<Integer, User> map = new HashMap<Integer, User>();
		if(pa != null)
		for(Message ms : pa.getDatas())
			map.put(ms.getFromId(), userService.GetUserById(ms.getFromId()));
		request.setAttribute("map", map);
		request.setAttribute("Page", pa);
		return PjaxUtils.get(request, "message");
	}
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping(value = "/send", produces = "text/html;charset=UTF-8")
	public String sendmessage(HttpServletRequest request, Integer id, String data){
		User user = (User) request.getSession().getAttribute("User");
		if(messageService.SendMessage(user.getId(), id, data, "")) return "ok";
		return "error";
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/sended")
	public String sendmessage(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		Map<Integer, User> map = new HashMap<Integer, User>();
		DPage<Message> pa = pageService.GetSendMessagePage(1, 10, user.getId());
		if(pa != null)
		for(Message ms : pa.getDatas())
			map.put(ms.getToId(), userService.GetUserById(ms.getToId()));
		request.setAttribute("map", map);
		request.setAttribute("Page", pa);
		return PjaxUtils.get(request, "tomessage");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/sended/{page}")
	public String sendmessage(HttpServletRequest request, @PathVariable Integer page){
		User user = (User) request.getSession().getAttribute("User");
		Map<Integer, User> map = new HashMap<Integer, User>();
		DPage<Message> pa = pageService.GetSendMessagePage(page, 10, user.getId());
		if(pa != null)
		for(Message ms : pa.getDatas())
			map.put(ms.getToId(), userService.GetUserById(ms.getToId()));
		request.setAttribute("map", map);
		request.setAttribute("Page", pa);
		return PjaxUtils.get(request, "tomessage");
	}
	
}
