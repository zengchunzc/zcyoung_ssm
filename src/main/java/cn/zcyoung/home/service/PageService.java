package cn.zcyoung.home.service;

import cn.zcyoung.home.pojo.News;
import cn.zcyoung.home.util.DPage;


public interface PageService {
	public DPage<News> GetNewsPage(int PageIndex, int PageSize);
	
}
