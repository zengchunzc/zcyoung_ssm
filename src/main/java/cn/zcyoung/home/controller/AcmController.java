package cn.zcyoung.home.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zcyoung.home.service.AcmService;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.web.auth.AuthPassport;

@Controller
@RequestMapping("/acm")
public class AcmController {
	@Resource
	private PageService pageService;
	@Resource
	private AcmService acmService;
	@Resource
	private UserService userService;
	
	@RequestMapping("/rank")
	public String rank(HttpServletRequest request){
		request.setAttribute("Page", pageService.GetAcmPage());
		return "acmrank";
	}
	@RequestMapping("/rank/{username}")
	public String rank1(HttpServletRequest request, @PathVariable String username){
		request.setAttribute("Page", pageService.GetAcmlogPage(username));
		request.setAttribute("U", userService.GetUserByUsername(username));
		return "acmuserrank";
	}
	
	@AuthPassport(isadmin = true)
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public String delete(String title, HttpServletRequest request){
		if(acmService.DeleteByContestName(title))
			request.setAttribute("msg", "删除成功！！");
		else request.setAttribute("msg", "删除失败！！");
		
		request.setAttribute("Page", pageService.GetAcmPage());
		return "acmrank";
	}
	@AuthPassport(isadmin = true)
	@RequestMapping(value =  "/add", method=RequestMethod.POST)
	public String add(String title, HttpServletRequest request, String html){
		if(acmService.AddContest(title, html))
			request.setAttribute("msg", "添加比赛信息成功!");
		else request.setAttribute("msg", "添加比赛信息失败！");
		
		request.setAttribute("Page", pageService.GetAcmPage());
		return "acmrank";
	}
	@AuthPassport(isadmin = true)
	@RequestMapping(value = "/newmonth")
	public String newmonth(HttpServletRequest request){
		if(acmService.newmonth()) request.setAttribute("msg", "更新成功！！");
		else request.setAttribute("msg", "更新失败！！");
		request.setAttribute("Page", pageService.GetAcmPage());
		return "acmrank";
	}
	
}
