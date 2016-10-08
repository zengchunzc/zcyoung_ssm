package cn.zcyoung.home.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.impl.PageServiceImpl;


@Controller
@RequestMapping("/view")
public class ViewController {
	@Resource
	public PageService pageService;
	
	@RequestMapping("/index")
	public String toIndex(String page, HttpServletRequest request){
		String str = "index"; if(request.getHeader("X-PJAX") != null) str = "pjax/" + str;
		
		int Ipage = 1;
		try {Ipage = Integer.parseInt(page);} catch (Exception e) {}
		request.setAttribute("Page", pageService.GetNewsPage(Ipage, 10));
		
		return "pjax/index";
	}
}
