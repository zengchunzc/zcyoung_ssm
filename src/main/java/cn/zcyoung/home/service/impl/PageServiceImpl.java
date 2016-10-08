package cn.zcyoung.home.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.NewsMapper;
import cn.zcyoung.home.pojo.News;
import cn.zcyoung.home.pojo.NewsExample;
import cn.zcyoung.home.service.PageService;
import cn.zcyoung.home.util.DPage;
import cn.zcyoung.home.util.Page;
@Service("pageService")
public class PageServiceImpl implements PageService{
	@Resource
	private NewsMapper newsMapper;

	@Override
	public DPage<News> GetNewsPage(int PageIndex, int PageSize) {
		NewsExample newsExample = new NewsExample();
		DPage<News> newsPage=new DPage<News>();
		newsPage.setPageIndex(PageIndex);
		newsPage.setPageSize(PageSize);
		newsPage.setTotalRecords(newsMapper.countByExample(newsExample));
		
		if(PageIndex <= 0) newsPage.setPageIndex(1);
		if(PageIndex > newsPage.getTotalPages()) newsPage.setPageIndex(newsPage.getTotalPages());
		
		newsExample.setPage(new Page((newsPage.getPageIndex()-1)*newsPage.getPageSize(), PageSize));
		newsPage.setDatas(newsMapper.selectByExample(newsExample));
		return newsPage;
	}

}
