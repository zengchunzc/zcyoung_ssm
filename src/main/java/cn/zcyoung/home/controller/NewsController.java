package cn.zcyoung.home.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zcyoung.home.service.NewsService;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.utils.PjaxUtils;

@Controller
@RequestMapping("/news")
public class NewsController {
	@Resource
	private PageService pageService;
	@Resource
	private NewsService newsService;
	
	@RequestMapping("")
	public String news(HttpServletRequest request){
		String key = request.getParameter("key");
		if(key == null || key.equals(""))
			request.setAttribute("Page", pageService.GetNewsPage(1, 10));
		else {
			request.setAttribute("Page", pageService.GetNewsPage(1, 10, key));
			request.setAttribute("Key", key);
		}
		return PjaxUtils.get(request, "newslist");
	}
	@RequestMapping("/{id}")
	public String news(@PathVariable Integer id, HttpServletRequest request){
		request.setAttribute("News", newsService.GetNewsById(id));
		return PjaxUtils.get(request, "news");
	}
	@RequestMapping("/list/{id}")
	public String newsl(@PathVariable Integer id, HttpServletRequest request){
		request.setAttribute("Page", pageService.GetNewsPage(id, 10));
		return PjaxUtils.get(request, "newslist");
	}
	@RequestMapping("/list/{id}/{key}")
	public String newsl(@PathVariable Integer id, @PathVariable String key, HttpServletRequest request){
		try {
			key = new String(key.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("Page", pageService.GetNewsPage(id, 10, key));
		request.setAttribute("Key", key);
		return PjaxUtils.get(request, "newslist");
	}
}
