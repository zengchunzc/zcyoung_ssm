package cn.zcyoung.home.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.NewsMapper;
import cn.zcyoung.home.pojo.News;
import cn.zcyoung.home.pojo.NewsExample;
import cn.zcyoung.home.service.NewsService;
import cn.zcyoung.home.utils.NewsUtils;
import cn.zcyoung.home.utils.Page;

@Service
public class NewsServiceImpl implements NewsService{
	@Resource
	private NewsMapper newsMapper;
	@Override
	public News GetNewsById(int id) {
		try {
			News news = newsMapper.selectByPrimaryKey(id);
			if(news == null) return null;
			news.setClick(news.getClick() + 1);
			if(news.getBody() == null || news.getBody().equals("") || news.getSource() == null || news.getSource().equals("")){
				News tn = NewsUtils.getNewsBodyByurl(news.getUrl());
				news.setBody(tn.getBody());
				if(tn.getSource() != null && !tn.getSource().equals("")) news.setSource(tn.getSource());
				else news.setSource("/news/" + news.getId());
			}
			UpdateNews(news);
			return news;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public News GetNewsByUrl(String url) {
		try {
			NewsExample newsExample = new NewsExample();
			newsExample.or().andUrlEqualTo(url);
			List<News> list = newsMapper.selectByExample(newsExample);
			if(list == null || list.size() < 1) return null;
			News news = list.get(0);
			return news;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<News> GetListNews() {
		try {
			NewsExample newsExample = new NewsExample();
			newsExample.setOrderByClause("id desc");
			return newsMapper.selectByExample(newsExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<News> GetListNews(int PageIndex, int PageSize) {
		try {
			NewsExample newsExample = new NewsExample();
			newsExample.setOrderByClause("id desc");
			newsExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return newsMapper.selectByExample(newsExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<News> GetListNews(int PageIndex, int PageSize,
			boolean ClickIncreace) {
		try {
			NewsExample newsExample = new NewsExample();
			if(ClickIncreace) newsExample.setOrderByClause("click asc");
			else newsExample.setOrderByClause("click desc");
			newsExample.or().andBodyIsNotNull();
			newsExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return newsMapper.selectByExample(newsExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<News> GetListNews(int PageIndex, int PageSize, String key) {
		try {
			NewsExample newsExample = new NewsExample();
			newsExample.setOrderByClause("id desc");
			newsExample.or().andNameLike("%" + key + "%");
			newsExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return newsMapper.selectByExample(newsExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean DeleteNews(int id) {
		try {
			return newsMapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateNews(News News) {
		try {
			return newsMapper.updateByPrimaryKeyWithBLOBs(News) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddNews(News news) {
		try {
			news.setSavetime(new Date());
			news.setClick(0);
			return newsMapper.insert(news) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int GetNewsCount() {
		try {
			return newsMapper.countByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetNewsCount(String key) {
		try {
			NewsExample newsExample = new NewsExample();
			newsExample.or().andNameLike("%" + key + "%");
			return newsMapper.countByExample(newsExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetNotNullBodyCount() {
		try {
			return newsMapper.countByBodyNotNull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



}
