package cn.zcyoung.home.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.ArticleService;
import cn.zcyoung.home.service.FriendUrlService;
import cn.zcyoung.home.service.MessageService;
import cn.zcyoung.home.service.NewsService;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.utils.GoForward;
import cn.zcyoung.home.utils.PjaxUtils;
import cn.zcyoung.home.web.auth.AuthPassport;

@Controller
@RequestMapping("/view")
public class ViewController {
	@Resource
	private PageService pageService;
	@Resource
	private MessageService messageService;
	@Resource
	private FriendUrlService friendUrlService;
	@Resource
	private NewsService newsService;
	@Resource
	private ArticleService articleService;
	@Resource
	private UserService userService;

	@RequestMapping("/article/{id}")
	public String article(@PathVariable Integer id, HttpServletRequest request){
		request.setAttribute("Article", articleService.GetArticleById(id)); 
		return PjaxUtils.get(request, "article");
	}

	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request){
		request.setAttribute("Page", pageService.GetNewsPage(1, 10));
		return PjaxUtils.get(request, "index");
	}
	@RequestMapping("/index/{page}")
	public String toIndexp(@PathVariable Integer page, HttpServletRequest request){
		request.setAttribute("Page", pageService.GetNewsPage(page, 10));
		return PjaxUtils.get(request, "index");
	}
	@RequestMapping("/friendurl")
	public String friendurl(HttpServletRequest request){
		request.setAttribute("Page", pageService.GetFriendUrlPage(FriendUrlService.By_TIME, 1, 10, false));
		return PjaxUtils.get(request, "friendurl");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/space/{username}")
	public String space(@PathVariable String username, HttpServletRequest request){
		User user = userService.GetUserByUsername(username);
		if(user == null){
			request.setAttribute("GoForward", new GoForward());
			return "goforward";
		}
		request.setAttribute("friend", user);
		return PjaxUtils.get(request, "space");
	}
	@RequestMapping("/friendurl/{page}")
	public String friendurl(@PathVariable Integer page, HttpServletRequest request){
		request.setAttribute("Page", pageService.GetFriendUrlPage(FriendUrlService.By_TIME, page, 10, false));
		return PjaxUtils.get(request, "friendurl");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/myfriend")
	public String myfriend(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("Page", pageService.GetFriendUserPage(1, 10, user.getId()));
		request.setAttribute("CPage", pageService.GetTmpFriendUserPage(user.getId()));
		return PjaxUtils.get(request, "myfriend");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/safe")
	public String safe(HttpServletRequest request){
		return PjaxUtils.get(request, "safe");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request){
		return PjaxUtils.get(request, "admin");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/loginhistory")
	public String loginhistory(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("Page", pageService.GetLoginHistoryPage(1, 10, user.getId()));
		return PjaxUtils.get(request, "login_history");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/loginhistory/{page}")
	public String loginhistory(@PathVariable Integer page, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("Page", pageService.GetLoginHistoryPage(page, 10, user.getId()));
		return PjaxUtils.get(request, "login_history");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/modifypassword")
	public String modifypassword(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("User", userService.GetUserById(user.getId()));
		return PjaxUtils.get(request, "modify_password");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("User", userService.GetUserById(user.getId()));
		return PjaxUtils.get(request, "modify");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/myfriend/{page}")
	public String myfriend(@PathVariable Integer page, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("Page", pageService.GetFriendUserPage(page, 10, user.getId()));
		return PjaxUtils.get(request, "myfriend");
	}
	@RequestMapping("/about")
	public String about(HttpServletRequest request){
		return PjaxUtils.get(request, "about");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/home")
	public String home(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("User", userService.GetUserById(user.getId()));
		return PjaxUtils.get(request, "home");
	}
	@RequestMapping("/bar")
	public String bar(String page,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("User");
		if(user!=null) request.setAttribute("messagecount", messageService.GetMessageCount(user.getId(), false));
		return "bar";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		return PjaxUtils.get(request, "login");
	}
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request){
		return PjaxUtils.get(request, "regist");
	}
	@RequestMapping("/exit")
	public String exit(){
		return "exit";
	}
	@RequestMapping("/api")
	public String api(HttpServletRequest request){
		return PjaxUtils.get(request, "api");
	}
	@RequestMapping("/findpassword")
	public String findpassword(HttpServletRequest request){
		return PjaxUtils.get(request, "findpassword");
	}
	@RequestMapping("/left")
	public String toLeft(HttpServletRequest request){
		request.setAttribute("FUPage", pageService.GetFriendUrlPage(FriendUrlService.By_CLICK, 1, 10, false));
		request.setAttribute("ArticlePage", pageService.GetArticlePage(ArticleService.By_TIME, 1, 6, false));
		return "left";
	}
	
}
