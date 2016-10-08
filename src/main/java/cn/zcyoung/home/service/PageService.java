package cn.zcyoung.home.service;

import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.pojo.LoginHistory;
import cn.zcyoung.home.pojo.Message;
import cn.zcyoung.home.pojo.News;
import cn.zcyoung.home.pojo.Article;
import cn.zcyoung.home.pojo.Ufile;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.utils.DPage;


public interface PageService {
	//新闻
	public DPage<News> GetNewsPage(int PageIndex, int PageSize);
	public DPage<News> GetNewsPage(int PageIndex, int PageSize, String key);
	//文章
	public DPage<Article> GetArticlePage(int type, int PageIndex, int PageSize, boolean includepri);
	public DPage<Article> GetArticlePage(int type, int PageIndex, int PageSize, boolean includepri, int userid);
	public DPage<Article> GetArticlePage(int type, int PageIndex, int PageSize, boolean includepri, String key);
	public DPage<Article> GetArticlePage(int type, int PageIndex, int PageSize, boolean includepri, int userid, String key);
	//友链
	public DPage<Friendurl> GetFriendUrlPage(int type, int PageIndex, int PageSize, boolean includeuncheck);
	public DPage<Friendurl> GetFriendUrlPage(int type, int PageIndex, int PageSize, boolean includeuncheck, int userid);
	//好友
	public DPage<User> GetFriendUserPage(int PageIndex, int PageSize, int userid);
	public DPage<User> GetTmpFriendUserPage(int userid);
	//信息
	public DPage<Message> GetMessagePage(int PageIndex, int PageSize, int userid);
	public DPage<Message> GetSendMessagePage(int PageIndex, int PageSize, int userid);
	//登录历史
	public DPage<LoginHistory> GetLoginHistoryPage(int PageIndex, int PageSize, int userid);
	//文件
	public DPage<Ufile> GetUfilePage(int PageIndex, int PageSize, boolean includepri);
	public DPage<Ufile> GetUfilePage(int PageIndex, int PageSize, boolean includepri, int userid);
	//用户
	public DPage<User> GetUserPage(int PageIndex, int PageSize);
	public DPage<User> GetUserPage(int PageIndex, int PageSize, String key);
}
