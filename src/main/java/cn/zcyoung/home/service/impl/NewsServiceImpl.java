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
			if(news.getBody() == null || news.getBody().equals("")){
				news.setClick(news.getClick() + 1);
				String body = NewsUtils.getNewsBodyByurl(news.getUrl()).getBody();
				if(news.getClick() > 10) news.setBody(body);
				UpdateNews(news);
				news.setBody(body);
			}
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
			return list != null && list.size() > 0 ? list.get(0) : null;
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
			return newsMapper.updateByPrimaryKey(News) > 0;
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

}
