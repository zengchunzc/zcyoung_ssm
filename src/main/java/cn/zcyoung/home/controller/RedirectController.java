package cn.zcyoung.home.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.service.FriendUrlService;

@Controller
@RequestMapping("/redirect")
public class RedirectController {
	
	@Resource
	private FriendUrlService friendUrlService;
	
	@RequestMapping("/friendurl/{id}")
	public String ref(@PathVariable Integer id, HttpServletRequest request){
		Friendurl fu = friendUrlService.GetFriendUrlById(id);
		if(fu == null) request.setAttribute("url", "/view/about");
		else {
			request.setAttribute("url", fu.getUrl());
			fu.setClick(fu.getClick() + 1);
			friendUrlService.UpdateFriendUrl(fu);
		}
		return "redirect";
	}
	
}
