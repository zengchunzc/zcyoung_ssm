package cn.zcyoung.home.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zcyoung.home.pojo.Article;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.ArticleService;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.utils.GoForward;
import cn.zcyoung.home.utils.HtmlUtils;
import cn.zcyoung.home.utils.PjaxUtils;
import cn.zcyoung.home.web.auth.AuthPassport;

@Controller
@RequestMapping("article")
public class ArticleController {
	@Resource
	private PageService pageService;
	@Resource
	private ArticleService articleService;
	@Resource
	private UserService userService;
	
	@RequestMapping("")
	public String articlelist(HttpServletRequest request){
		String key = request.getParameter("key");
		if(key == null || key.equals(""))
			request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, 1, 10, false));
		else{
			request.setAttribute("Key", key);
			request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, 1, 10, false, key));
		}
		return PjaxUtils.get(request, "articlelist");
	}
	
	@RequestMapping("/{id}")
	public String article(@PathVariable Integer id, HttpServletRequest request){
		Article article = articleService.GetArticleById(id);
		User user = (User) request.getSession().getAttribute("User");
		if(article == null || article.getState() != 1) {
			request.setAttribute("GoForward", new GoForward("文章不存在,请稍后访问,3s后跳转.", "3000", "/article"));
			return "goforward";
		}
		if(article.getReadpower() == 1){
			if(user == null || (user != null && user.getId() != article.getUserId())) {
				request.setAttribute("GoForward", new GoForward("文章不存在,请稍后访问,3s后跳转.", "3000", "/article"));
				return "goforward";
			}
		}
		user = userService.GetUserById(article.getUserId());
		article.setClick(article.getClick() + 1);
		articleService.UpdateArticle(article);
		request.setAttribute("Article", article); 
		request.setAttribute("author", user);
		return PjaxUtils.get(request, "article");
	}
	@RequestMapping("/list/{id}")
	public String articlel(@PathVariable Integer id, HttpServletRequest request){
		request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, id, 10, false));
		return PjaxUtils.get(request, "articlelist");
	}
	@RequestMapping("/list/{id}/{key}")
	public String articlel(@PathVariable Integer id,@PathVariable String key,  HttpServletRequest request){
		request.setAttribute("Key", key);
		request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, id, 10, false, key));
		return PjaxUtils.get(request, "articlelist");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/friend/{username}")
	public String fu(HttpServletRequest request, @PathVariable String username){
		User user = userService.GetUserByUsername(username);
		if(user == null) {
			request.setAttribute("GoForward", new GoForward("错误，3s..", "3000", "/article/my"));
			return "goforward";
		}
		request.setAttribute("friend", user);
		String key = request.getParameter("key");
		if(key == null || key .equals("")){
			request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, 1, 10, false, user.getId()));
		}else{
			request.setAttribute("Key", key);
			request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, 1, 10, false, user.getId(), key));
		}
		return PjaxUtils.get(request, "articlelist_friend");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/friend/{username}/{page}")
	public String fu(HttpServletRequest request, @PathVariable String username, @PathVariable Integer page){
		User user = userService.GetUserByUsername(username);
		if(user == null) {
			request.setAttribute("GoForward", new GoForward("错误，3s..", "3000", "/article/my"));
			return "goforward";
		}
		request.setAttribute("friend", user);
		request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, page, 10, false, user.getId()));
		return PjaxUtils.get(request, "articlelist_friend");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/friend/{username}/{page}/{key}")
	public String fu(HttpServletRequest request, @PathVariable String username, @PathVariable Integer page, @PathVariable String key){
		User user = userService.GetUserByUsername(username);
		if(user == null) {
			request.setAttribute("GoForward", new GoForward("错误，3s..", "3000", "/article/my"));
			return "goforward";
		}
		request.setAttribute("friend", user);
		request.setAttribute("Key", key);
		request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, page, 10, false, user.getId(), key));
		return PjaxUtils.get(request, "articlelist_friend");
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/my")
	public String my(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		String key = request.getParameter("key");
		if(key == null || key.equals(""))
			request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, 1, 10, true, user.getId()));
		else{
			request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, 1, 10, true, user.getId(), key));
			request.setAttribute("Key", key);
		}
		return PjaxUtils.get(request, "myarticle");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/my/{page}")
	public String my(@PathVariable Integer page, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, page, 10, true, user.getId()));
		return PjaxUtils.get(request, "myarticle");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/my/{page}/{key}")
	public String my(@PathVariable Integer page, @PathVariable String key, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		request.setAttribute("Key", key);
		request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, page, 10, true, user.getId(), key));
		return PjaxUtils.get(request, "myarticle");
	}
	@AuthPassport(isuser = true)
	@RequestMapping("/write")
	public String write(){
		return "writearticle";
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/rewrite/{id}")
	public String rewrite(HttpServletRequest request, @PathVariable Integer id){
		Article article = articleService.GetArticleById(id);
		User user = (User) request.getSession().getAttribute("User");
		if(article != null && article.getUserId() == user.getId()){
			request.setAttribute("Article", article);
		}
		else {
			request.setAttribute("GoForward", new GoForward("错误，3s..", "3000", "/article/my"));
			return "goforward";
		}
		return "updatearticle";
	}
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping(value="/update", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String update(HttpServletRequest request,String title,String gk,String MyArticle,Integer id){
		Article article = articleService.GetArticleById(id);
		User user = (User) request.getSession().getAttribute("User");
		if(article.getUserId() != user.getId()){
			return "error:这个文章不是你的。";
		}
		article.setTitle(title);
		article.setReadpower(gk.equals("是")?2:1);
		article.setBody(MyArticle);
		article.setuTime(new Date());
		if(articleService.UpdateArticle(article)) return "ok";
		return "error:未知错误";
	}
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String add(HttpServletRequest request,String title,String gk,String MyArticle){
		User user = (User) request.getSession().getAttribute("User");
		if(title==null||title.equals("")||gk==null||gk.equals("")||MyArticle==null||MyArticle.equals("")){
			return "error:不能有为空项。";
		}
		Article article=new Article();
		article.setTitle(HtmlUtils.delHTMLTag(title, 200));
		article.setReadpower(gk.equals("是")?2:1);
		article.setBody(MyArticle);
		article.setUserId(user.getId());
		article.setwTime(new Date());
		article.setuTime(new Date());
		article.setState(1);
		article.setClick(0);
		article.setBodyPre(HtmlUtils.delHTMLTag(MyArticle, 200));
		if(articleService.AddArticle(article)){
			return "ok";
		}
		return "error:由于未知原因，发布失败。";
	}
	
	@AuthPassport(isuser = true)
	@RequestMapping("/delete/{id}")
	public String delete(HttpServletRequest request, @PathVariable Integer id){
		User user = (User) request.getSession().getAttribute("User");
		Article article = articleService.GetArticleById(id);
		if(article == null || article.getUserId() != user.getId()){
			request.setAttribute("msg", "系统错误。");
		}else{
			if(articleService.DeleteArticle(id, false)){
				request.setAttribute("msg", "删除成功。");
			}else request.setAttribute("msg", "删除失败。");
		}
		request.setAttribute("Page", pageService.GetArticlePage(ArticleService.By_TIME, 1, 10, true, user.getId()));
		return PjaxUtils.get(request, "myarticle");
	}
	
}
