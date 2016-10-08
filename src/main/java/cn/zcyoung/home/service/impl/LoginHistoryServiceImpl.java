package cn.zcyoung.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.LoginHistoryMapper;
import cn.zcyoung.home.pojo.LoginHistory;
import cn.zcyoung.home.pojo.LoginHistoryExample;
import cn.zcyoung.home.service.LoginHistoryService;
import cn.zcyoung.home.utils.Page;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService{

	@Resource
	private LoginHistoryMapper loginHistoryMapper;
	@Override
	public LoginHistory GetLoginHistoryById(int id) {
		try {
			return loginHistoryMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LoginHistory> GetListLoginHistory(int PageIndex, int PageSize) {
		try {
			LoginHistoryExample loginHistoryExample = new LoginHistoryExample();
			loginHistoryExample.setOrderByClause("l_time desc");
			loginHistoryExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return loginHistoryMapper.selectByExample(loginHistoryExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LoginHistory> GetListLoginHistory(int PageIndex, int PageSize,
			int userid) {
		try {
			LoginHistoryExample loginHistoryExample = new LoginHistoryExample();
			loginHistoryExample.setOrderByClause("l_time desc");
			loginHistoryExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			loginHistoryExample.or().andUserIdEqualTo(userid);
			return loginHistoryMapper.selectByExample(loginHistoryExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean DeleteLoginHistory(int id) {
		try {
			return loginHistoryMapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateLoginHistory(LoginHistory LoginHistory) {
		try {
			return loginHistoryMapper.updateByPrimaryKey(LoginHistory) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddLoginHistory(LoginHistory LoginHistory) {
		try {
			return loginHistoryMapper.insert(LoginHistory) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int GetLoginHistoryCount(int userid) {
		try {
			LoginHistoryExample loginHistoryExample = new LoginHistoryExample();
			loginHistoryExample.or().andUserIdEqualTo(userid);
			return loginHistoryMapper.countByExample(loginHistoryExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetLoginHistoryCount() {
		try {
			return loginHistoryMapper.countByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
