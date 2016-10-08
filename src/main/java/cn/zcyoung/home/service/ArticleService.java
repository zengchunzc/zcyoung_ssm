package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.Article;

public interface ArticleService {
	public static int By_CLICK = 1;
	public static int By_TIME = 2;
	//根据Id找
	public Article GetArticleById(int id);
	//获取全部
	public List<Article> GetListArticle(int type, boolean includepri, int userid);
	public List<Article> GetListArticle(int type, boolean includepri, int PageIndex, int PageSize);
	public List<Article> GetListArticle(int type, boolean includepri, int PageIndex, int PageSize, int userid);
	public List<Article> GetListArticle(int type, boolean includepri, int PageIndex, int PageSize, String key);
	public List<Article> GetListArticle(int type, boolean includepri, int PageIndex, int PageSize, int userid, String key);
	//删除
	public boolean DeleteArticle(int id, boolean deleteinsql);
	//更新
	public boolean UpdateArticle(Article Article);
	//增加
	public boolean AddArticle(Article Article);
	//总数
	public int GetArticleCount(boolean includepri, int userid);
	public int GetArticleCount(boolean includepri);
	public int GetArticleCount(boolean includepri, int userid, String key);
	public int GetArticleCount(boolean includepri, String key);

}
