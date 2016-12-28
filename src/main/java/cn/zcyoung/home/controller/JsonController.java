package cn.zcyoung.home.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





















import com.alibaba.fastjson.JSON;

import cn.zcyoung.home.pojo.Address;
import cn.zcyoung.home.pojo.Article;
import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.pojo.JsonArticle;
import cn.zcyoung.home.pojo.JsonFile;
import cn.zcyoung.home.pojo.LoginHistory;
import cn.zcyoung.home.pojo.News;
import cn.zcyoung.home.pojo.Ufile;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.AddressService;
import cn.zcyoung.home.service.ArticleService;
import cn.zcyoung.home.service.FriendUrlService;
import cn.zcyoung.home.service.LoginHistoryService;
import cn.zcyoung.home.service.NewsService;
import cn.zcyoung.home.service.UfileService;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.web.auth.AuthPassport;

@Controller
@RequestMapping("/json")
public class JsonController {

	@Resource
	private AddressService addressService;
	@Resource
	private NewsService newsService;
	@Resource
	private UserService userService;
	@Resource
	private LoginHistoryService loginHistoryService;
	@Resource
	private FriendUrlService friendUrlService;
	@Resource
	private ArticleService articleService;
	@Resource
	private UfileService ufileService;

	@ResponseBody
	@RequestMapping("listarticle")
	public List<JsonArticle> glistA(HttpServletRequest request, int PageIndex, int PageSize, int type, String key, Integer userid){
		if(PageIndex < 1) PageIndex = 1;
		List<Article> listArticles;
		if(key != null && !key.equals("")) {
			if(userid == null) listArticles = articleService.GetListArticle(type, false, PageIndex, PageSize, key);
			else listArticles = articleService.GetListArticle(type, false, PageIndex, PageSize, userid, key);
		}
		else {
			if(userid == null) listArticles = articleService.GetListArticle(type, false, PageIndex, PageSize);
			else listArticles = articleService.GetListArticle(type, false, PageIndex, PageSize, userid);
		}
		List<JsonArticle> listJsonArticles = new ArrayList<JsonArticle>();
		for(Article article : listArticles){
			JsonArticle ja = new JsonArticle();
			ja.setArticle(article);
			User user = userService.GetUserById(article.getUserId());
			ja.setNickname(user.getNickname());
			ja.setUserpic(user.getPic());
			listJsonArticles.add(ja);
		}
		return listJsonArticles;
	}
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping("myarticle")
	public List<JsonArticle> myarticle(HttpServletRequest request,int PageIndex, int PageSize, int type, String key){
		User user = (User) request.getSession().getAttribute("User");
		if(PageIndex < 1) PageIndex = 1;
		List<Article> listArticles;
		if(key != null && !key.equals("")) {
			listArticles = articleService.GetListArticle(type, false, PageIndex, PageSize, user.getId(), key);
		}
		else {
			listArticles = articleService.GetListArticle(type, false, PageIndex, PageSize, user.getId());
		}
		List<JsonArticle> listJsonArticles = new ArrayList<JsonArticle>();
		for(Article article : listArticles){
			JsonArticle ja = new JsonArticle();
			ja.setArticle(article);
			ja.setNickname(user.getNickname());
			ja.setUserpic(user.getPic());
			listJsonArticles.add(ja);
		}
		return listJsonArticles;
	}
	
	
	@ResponseBody
	@RequestMapping("listfile")
	public List<JsonFile> glistB(HttpServletRequest request, int PageIndex, int PageSize, Integer userid){
		if(PageIndex < 1) PageIndex = 1;
		List<Ufile> listUfiles;
		if(userid == null) listUfiles = ufileService.GetListUfile(false, PageIndex, PageSize);
		else listUfiles = ufileService.GetListUfile(false, PageIndex, PageSize, userid);
		List<JsonFile> listJsonFiles = new ArrayList<JsonFile>();
		for(Ufile ufile : listUfiles){
			JsonFile jf = new JsonFile();
			jf.setUfile(ufile);
			User user = userService.GetUserById(ufile.getUserId());
			jf.setNickname(user.getNickname());
			jf.setUserpic(user.getPic());
			listJsonFiles.add(jf);
		}
		return listJsonFiles;
	}
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping("myfile")
	public List<JsonFile> myfile(HttpServletRequest request, int PageIndex, int PageSize){
		User user = (User) request.getSession().getAttribute("User");
		if(PageIndex < 1) PageIndex = 1;
		List<Ufile> listUfiles;
		listUfiles = ufileService.GetListUfile(true, PageIndex, PageSize, user.getId());
		List<JsonFile> listJsonFiles = new ArrayList<JsonFile>();
		for(Ufile ufile : listUfiles){
			JsonFile jf = new JsonFile();
			jf.setUfile(ufile);
			jf.setNickname(user.getNickname());
			jf.setUserpic(user.getPic());
			listJsonFiles.add(jf);
		}
		return listJsonFiles;
	}
	
	
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping("/userinfo")
	public User user2(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		user = userService.GetUserById(user.getId());
		user.setPassword("就不告诉你");
		return user;
	}
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping("/listaddress")
	public List<Address> listaddress(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("User");
		return addressService.GetListAddress(user.getId());
	}
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping(value = "/upload", produces = "text/html;charset=UTF-8")
	public String deleteall(HttpServletRequest request, String json){
		User user = (User) request.getSession().getAttribute("User");
		List<Address> list = JSON.parseArray(json, Address.class);
		if(list.size() > 0){
			if(!addressService.DeleteAll(user.getId())) return "error";
			for(Address address : list){
				addressService.AddAddress(address);
			}
		}
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listnews")
	public List<News> getlistnews(int PageIndex, int PageSize, String key){
		if(PageIndex < 1) PageIndex = 1;
		List<News> list;
		if(key != null && !key.equals("")) list = newsService.GetListNews(PageIndex, PageSize, key);
		else list = newsService.GetListNews(PageIndex, PageSize);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/news")
	public News getnews(int id){
		return newsService.GetNewsById(id);
	}
	
	@ResponseBody
	@RequestMapping("/article")
	public JsonArticle getarticle(HttpServletRequest request, int id){
		Article article = articleService.GetArticleById(id);
		JsonArticle jsonArticle = new JsonArticle();
		jsonArticle.setArticle(article);
		User users = userService.GetUserById(article.getUserId());
		jsonArticle.setNickname(users.getNickname());
		jsonArticle.setUserpic(users.getPic());
		if(article == null || article.getState() != 1) return null;
		if(article.getReadpower() == 1){
			User user = (User) request.getSession().getAttribute("User");
			if(user == null || user.getId() != article.getUserId()) 
				return null;
		}
		article.setClick(article.getClick() + 1);
		articleService.UpdateArticle(article);
		return jsonArticle;
	}
	
	
	@AuthPassport(isuser = true)
	@ResponseBody
	@RequestMapping("/loginhistory")
	public List<LoginHistory> getlh(HttpServletRequest request, int PageIndex, int PageSize){
		if(PageIndex < 1) PageIndex = 1;
		return loginHistoryService.GetListLoginHistory(PageIndex, PageSize);
	}

	@ResponseBody
	@RequestMapping("/friendurl")
	public List<Friendurl> getfl(int PageIndex, int PageSize, int type){
		if(PageIndex < 1) PageIndex = 1;
		return friendUrlService.GetListFriendUrl(type, false, PageIndex, PageSize);
	}
}
