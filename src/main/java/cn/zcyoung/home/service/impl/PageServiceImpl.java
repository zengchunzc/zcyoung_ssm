package cn.zcyoung.home.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.zcyoung.home.pojo.Acmlog;
import cn.zcyoung.home.pojo.Article;
import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.pojo.LoginHistory;
import cn.zcyoung.home.pojo.Message;
import cn.zcyoung.home.pojo.News;
import cn.zcyoung.home.pojo.Ufile;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.AcmService;
import cn.zcyoung.home.service.ArticleService;
import cn.zcyoung.home.service.FriendUrlService;
import cn.zcyoung.home.service.LoginHistoryService;
import cn.zcyoung.home.service.MessageService;
import cn.zcyoung.home.service.NewsService;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.service.UfileService;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.utils.DPage;
import cn.zcyoung.home.utils.PAcm;

@Component
public class PageServiceImpl implements PageService{
	@Resource
	private NewsService newsService;
	@Resource
	private ArticleService articleService;
	@Resource
	private FriendUrlService friendUrlService;
	@Resource
	private UserService userService;
	@Resource
	private MessageService messageService;
	@Resource
	private LoginHistoryService loginHistoryService;
	@Resource
	private UfileService ufileService;
	@Resource 
	private AcmService acmService;
	
	@Override
	public DPage<News> GetNewsPage(int PageIndex, int PageSize) {
		DPage<News> page = new DPage<News>();
		if(!init(page, PageIndex, PageSize, newsService.GetNewsCount())) return null;
		page.setDatas(newsService.GetListNews(page.getPageIndex(), PageSize));
		return page;
	}

	@Override
	public DPage<News> GetNewsPage(int PageIndex, int PageSize, String key) {
		DPage<News> page = new DPage<News>();
		if(!init(page, PageIndex, PageSize, newsService.GetNewsCount(key))) return null;
		page.setDatas(newsService.GetListNews(page.getPageIndex(), PageSize, key));
		return page;
	}
	
	@Override
	public DPage<Article> GetArticlePage(int type, int PageIndex, int PageSize,
			boolean includepri) {
		DPage<Article> page = new DPage<Article>();
		if(!init(page, PageIndex, PageSize, articleService.GetArticleCount(includepri))) return null;
		page.setDatas(articleService.GetListArticle(type, includepri, page.getPageIndex(), PageSize));
		return page;
	}

	@Override
	public DPage<Article> GetArticlePage(int type, int PageIndex, int PageSize,
			boolean includepri, int userid) {
		DPage<Article> page = new DPage<Article>();
		if(!init(page, PageIndex, PageSize, articleService.GetArticleCount(includepri, userid))) return null;
		page.setDatas(articleService.GetListArticle(type, includepri, page.getPageIndex(), PageSize, userid));
		return page;
	}

	@Override
	public DPage<Article> GetArticlePage(int type, int PageIndex, int PageSize,
			boolean includepri, String key) {
		DPage<Article> page = new DPage<Article>();
		if(!init(page, PageIndex, PageSize, articleService.GetArticleCount(includepri, key))) return null;
		page.setDatas(articleService.GetListArticle(type, includepri, page.getPageIndex(), PageSize, key));
		return page;
	}

	@Override
	public DPage<Article> GetArticlePage(int type, int PageIndex, int PageSize,
			boolean includepri, int userid, String key) {
		DPage<Article> page = new DPage<Article>();
		if(!init(page, PageIndex, PageSize,articleService.GetArticleCount(includepri, userid, key))) return null;
		page.setDatas(articleService.GetListArticle(type, includepri, page.getPageIndex(), PageSize, userid, key));
		return page;
	}
	
	@Override
	public DPage<Friendurl> GetFriendUrlPage(int type, int PageIndex,
			int PageSize, boolean includeuncheck) {
		DPage<Friendurl> page = new DPage<Friendurl>();
		if(!init(page, PageIndex, PageSize, friendUrlService.GetFriendUrlCount(type, includeuncheck))) return null;
		page.setDatas(friendUrlService.GetListFriendUrl(type, includeuncheck, page.getPageIndex(), PageSize));
		return page;
	}

	@Override
	public DPage<Friendurl> GetFriendUrlPage(int type, int PageIndex,
			int PageSize, boolean includeuncheck, int userid) {
		DPage<Friendurl> page = new DPage<Friendurl>();
		if(!init(page, PageIndex, PageSize, friendUrlService.GetFriendUrlCount(type, includeuncheck, userid))) return null;
		page.setDatas(friendUrlService.GetListFriendUrl(type, includeuncheck, page.getPageIndex(), PageSize, userid));
		return page;
	}

	@Override
	public DPage<User> GetFriendUserPage(int PageIndex, int PageSize, int userid) {
		DPage<User> page = new DPage<User>();
		if(!init(page, PageIndex, PageSize, userService.GetUserFriendCount(userid))) return null;
		page.setDatas(userService.GetFriendList(userid, (page.getPageIndex() - 1) * PageSize, PageSize));
		return page;
	}
	
	
	public boolean init(DPage<?> page, int PageIndex, int PageSize, int count){
		if(count == 0) return false;
		if(PageIndex <= 0 ) PageIndex =1;
		page.setPageIndex(PageIndex);
		page.setPageSize(PageSize);
		page.setTotalRecords(count);
		if(PageIndex > page.getTotalPages()) page.setPageIndex(page.getTotalPages());
		return true;
	}

	@Override
	public DPage<User> GetTmpFriendUserPage(int userid) {
		DPage<User> page = new DPage<User>();
		if(!init(page, 1, 9999, userService.GetUserFriendCount(userid))) return null;
		page.setDatas(userService.GetTmpFriendList(userid));
		return page;
	}

	@Override
	public DPage<Message> GetMessagePage(int PageIndex, int PageSize, int userid) {
		DPage<Message> page = new DPage<Message>();
		if(!init(page, PageIndex, PageSize, messageService.GetMessageCount(userid))) return null;
		page.setDatas(messageService.GetListMessage(userid, page.getPageIndex(), PageSize));
		return page;
	}

	@Override
	public DPage<Message> GetSendMessagePage(int PageIndex, int PageSize,
			int userid) {
		DPage<Message> page = new DPage<Message>();
		if(!init(page, PageIndex, PageSize, messageService.GetMessageSendCount(userid))) return null;
		page.setDatas(messageService.GetListSendMessage(userid, page.getPageIndex(), PageSize));
		return page;
	}

	@Override
	public DPage<LoginHistory> GetLoginHistoryPage(int PageIndex, int PageSize,
			int userid) {
		DPage<LoginHistory> page = new DPage<LoginHistory>();
		if(!init(page, PageIndex, PageSize, loginHistoryService.GetLoginHistoryCount(userid))) return null;
		page.setDatas(loginHistoryService.GetListLoginHistory(page.getPageIndex(), PageSize, userid));
		return page;
	}

	@Override
	public DPage<Ufile> GetUfilePage(int PageIndex, int PageSize,
			boolean includepri) {
		DPage<Ufile> page = new DPage<Ufile>();
		if(!init(page, PageIndex, PageSize, ufileService.GetUfileCount(includepri))) return null;
		page.setDatas(ufileService.GetListUfile(includepri, page.getPageIndex(), PageSize));
		return page;
	}

	@Override
	public DPage<Ufile> GetUfilePage(int PageIndex, int PageSize,
			boolean includepri, int userid) {
		DPage<Ufile> page = new DPage<Ufile>();
		if(!init(page, PageIndex, PageSize, ufileService.GetUfileCount(includepri, userid))) return null;
		page.setDatas(ufileService.GetListUfile(includepri, page.getPageIndex(), PageSize, userid));
		return page;
	}

	@Override
	public DPage<User> GetUserPage(int PageIndex, int PageSize) {
		DPage<User> page = new DPage<User>();
		if(!init(page, PageIndex, PageSize, userService.GetUserCount())) return null;
		page.setDatas(userService.GetListUser(page.getPageIndex(), PageSize));
		return page;
	}

	@Override
	public DPage<User> GetUserPage(int PageIndex, int PageSize, String key) {
		DPage<User> page = new DPage<User>();
		if(!init(page, PageIndex, PageSize, userService.GetUserCount(key))) return null;
		page.setDatas(userService.GetListUser(page.getPageIndex(), PageSize, key));
		return page;
	}

	@Override
	public DPage<PAcm> GetAcmPage() {
		DPage<PAcm> page = new DPage<PAcm>();
		List<PAcm> list = new ArrayList<PAcm>();
		
		List<User> listuser = userService.GetListUser();
		for(User u : listuser){
			if(u.getUsername().length() == 10 && u.getUsername().startsWith("1")){
				PAcm acm = new PAcm();
				acm.setUser(u);
				acm.setYear(acmService.GetYearScore(u.getUsername()));
				acm.setMonth(acmService.GetMonthScore(u.getUsername()));
				//acm.setList(acmService.GetListAcmLog(u.getUsername(), false));
				list.add(acm);
			}
		}
		Collections.sort(list);
		page.setDatas(list);
		page.setPageIndex(1);
		page.setTotalPages(1);
		page.setPageSize(99999);
		return page;
	}

	@Override
	public DPage<Acmlog> GetAcmlogPage(String no) {
		DPage<Acmlog> page = new DPage<Acmlog>();
		List<Acmlog> list = acmService.GetListAcmLog(no, false);
		page.setDatas(list);
		page.setPageIndex(1);
		page.setTotalPages(1);
		page.setPageSize(99999);
		return page;
	}
}





