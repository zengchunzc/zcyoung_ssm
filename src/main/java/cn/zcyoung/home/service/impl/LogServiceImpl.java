package cn.zcyoung.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.LogMapper;
import cn.zcyoung.home.pojo.Log;
import cn.zcyoung.home.pojo.LogExample;
import cn.zcyoung.home.service.LogService;
import cn.zcyoung.home.utils.Page;

@Service
public class LogServiceImpl implements LogService{
	@Resource
	private LogMapper logMapper;
	@Override
	public Log GetLogById(int id) {
		try {
			return logMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Log> GetListLog(int PageIndex, int PageSize) {
		try {
			LogExample logExample = new LogExample();
			logExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Log> GetListLog(int PageIndex, int PageSize, int userid) {
		try {
			LogExample logExample = new LogExample();
			logExample.or().andUserIdEqualTo(userid);
			logExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Log> GetListLog(int PageIndex, int PageSize, int userid,
			String type) {
		try {
			LogExample logExample = new LogExample();
			logExample.or().andUserIdEqualTo(userid).andTypeEqualTo(type);
			logExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean DeleteLog(int id) {
		try {
			return logMapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateLog(Log log) {
		try {
			return logMapper.updateByPrimaryKey(log) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddLog(Log log) {
		try {
			return logMapper.insert(log) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int GetLogCount(int userid) {
		try {
			LogExample logExample = new LogExample();
			logExample.or().andUserIdEqualTo(userid);
			return logMapper.countByExample(logExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetLogCount(int userid, String type) {
		try {
			LogExample logExample = new LogExample();
			logExample.or().andTypeEqualTo(type);
			return logMapper.countByExample(logExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Log> GetListLog(int PageIndex, int PageSize, String type) {
		try {
			LogExample logExample = new LogExample();
			logExample.or().andTypeEqualTo(type);
			logExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return logMapper.selectByExample(logExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int GetLogCount() {
		try {
			return logMapper.countByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
