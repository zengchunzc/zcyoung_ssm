package cn.zcyoung.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.ArticleMapper;
import cn.zcyoung.home.pojo.Article;
import cn.zcyoung.home.pojo.ArticleExample;
import cn.zcyoung.home.service.ArticleService;
import cn.zcyoung.home.utils.Page;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Resource
	private ArticleMapper articleMapper;
	@Override
	public Article GetArticleById(int id) {
		try {
			return articleMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Article> GetListArticle(int type, boolean includepri, int userid) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			if(type == ArticleService.By_CLICK) articleExample.setOrderByClause("click desc");
			if(type == ArticleService.By_TIME) articleExample.setOrderByClause("u_time desc");
			if(!includepri) criteria.andReadpowerEqualTo(2);
			criteria.andUserIdEqualTo(userid);
			criteria.andStateEqualTo(1);
			return articleMapper.selectByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Article> GetListArticle(int type, boolean includepri,
			int PageIndex, int PageSize) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			if(type == ArticleService.By_CLICK) articleExample.setOrderByClause("click desc");
			if(type == ArticleService.By_TIME) articleExample.setOrderByClause("u_time desc");
			if(!includepri) criteria.andReadpowerEqualTo(2);
			criteria.andStateEqualTo(1);
			articleExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return articleMapper.selectByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Article> GetListArticle(int type, boolean includepri,
			int PageIndex, int PageSize, int userid) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			if(type == ArticleService.By_CLICK) articleExample.setOrderByClause("click desc");
			if(type == ArticleService.By_TIME) articleExample.setOrderByClause("u_time desc");
			if(!includepri) criteria.andReadpowerEqualTo(2);
			criteria.andUserIdEqualTo(userid);
			criteria.andStateEqualTo(1);
			articleExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return articleMapper.selectByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Article> GetListArticle(int type, boolean includepri,
			int PageIndex, int PageSize, String key) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			if(type == ArticleService.By_CLICK) articleExample.setOrderByClause("click desc");
			if(type == ArticleService.By_TIME) articleExample.setOrderByClause("u_time desc");
			if(!includepri) criteria.andReadpowerEqualTo(2);
			criteria.andStateEqualTo(1);
			criteria.andTitleLike("%"+ key +"%");
			articleExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return articleMapper.selectByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Article> GetListArticle(int type, boolean includepri,
			int PageIndex, int PageSize, int userid, String key) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			if(type == ArticleService.By_CLICK) articleExample.setOrderByClause("click desc");
			if(type == ArticleService.By_TIME) articleExample.setOrderByClause("u_time desc");
			if(!includepri) criteria.andReadpowerEqualTo(2);
			criteria.andUserIdEqualTo(userid);
			criteria.andStateEqualTo(1);
			criteria.andTitleLike("%"+ key +"%");
			articleExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return articleMapper.selectByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean DeleteArticle(int id, boolean deleteinsql) {
		try {
			if(deleteinsql) return articleMapper.deleteByPrimaryKey(id) > 0;
			else {
				Article article = GetArticleById(id);
				article.setState(2);
				return UpdateArticle(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateArticle(Article Article) {
		try {
			return articleMapper.updateByPrimaryKeyWithBLOBs(Article) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddArticle(Article Article) {
		try {
			return articleMapper.insert(Article) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int GetArticleCount(boolean includepri, int userid) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			criteria.andStateEqualTo(1);
			if(!includepri) criteria.andReadpowerEqualTo(2);
			criteria.andUserIdEqualTo(userid);
			return articleMapper.countByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetArticleCount(boolean includepri) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			criteria.andStateEqualTo(1);
			if(!includepri) criteria.andReadpowerEqualTo(2);
			return articleMapper.countByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetArticleCount(boolean includepri, int userid, String key) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			criteria.andStateEqualTo(1);
			if(!includepri) criteria.andReadpowerEqualTo(2);
			criteria.andUserIdEqualTo(userid);
			criteria.andTitleLike("%" + key + "%");
			return articleMapper.countByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetArticleCount(boolean includepri, String key) {
		try {
			ArticleExample articleExample = new ArticleExample();
			ArticleExample.Criteria criteria = articleExample.createCriteria();
			criteria.andStateEqualTo(1);
			criteria.andTitleLike("%" + key + "%");
			if(!includepri) criteria.andReadpowerEqualTo(2);
			return articleMapper.countByExample(articleExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
