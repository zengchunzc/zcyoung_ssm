package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.News;


public interface NewsService {
	//根据Id找
	public News GetNewsById(int id);
	//根据url
	public News GetNewsByUrl(String url);
	//获取全部
	public List<News> GetListNews();
	public List<News> GetListNews(int PageIndex, int PageSize);
	public List<News> GetListNews(int PageIndex, int PageSize, boolean ClickIncreace);
	public List<News> GetListNews(int PageIndex, int PageSize, String key);
	//删除
	public boolean DeleteNews(int id);
	//更新
	public boolean UpdateNews(News News);
	//增加
	public boolean AddNews(News News);
	//总数
	public int GetNewsCount();
	public int GetNewsCount(String key);
	public int GetNotNullBodyCount();
}
