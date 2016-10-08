package cn.zcyoung.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.UfileMapper;
import cn.zcyoung.home.pojo.Ufile;
import cn.zcyoung.home.pojo.UfileExample;
import cn.zcyoung.home.service.UfileService;
import cn.zcyoung.home.utils.Page;
@Service
public class UfileServiceImpl implements UfileService {
	
	@Resource
	private UfileMapper ufileMapper;
	@Override
	public Ufile GetUfileByAddress(String address) {
		try {
			UfileExample ufileExample = new UfileExample();
			ufileExample.or().andAddressEqualTo(address);
			List<Ufile> list = ufileMapper.selectByExample(ufileExample);
			return list != null && list.size() > 0 ? list.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Ufile GetUfileById(int id) {
		try {
			return ufileMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Ufile> GetListUfile(boolean includepri, int userid) {
		try {
			UfileExample ufileExample = new UfileExample();
			ufileExample.setOrderByClause("time desc");
			ufileExample.or().andUserIdEqualTo(userid);
			return ufileMapper.selectByExample(ufileExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Ufile> GetListUfile(boolean includepri, int PageIndex,
			int PageSize) {
		try {
			UfileExample ufileExample = new UfileExample();
			ufileExample.setOrderByClause("time desc");
			if(!includepri)  ufileExample.or().andPriEqualTo(0);
			ufileExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return ufileMapper.selectByExample(ufileExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Ufile> GetListUfile(boolean includepri, int PageIndex,
			int PageSize, int userid) {
		try {
			UfileExample ufileExample = new UfileExample();
			ufileExample.setOrderByClause("time desc");
			if(includepri) ufileExample.or().andUserIdEqualTo(userid);
			else ufileExample.or().andUserIdEqualTo(userid).andPriEqualTo(0);
			ufileExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return ufileMapper.selectByExample(ufileExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean DeleteUfile(int id) {
		try {
			return ufileMapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateUfile(Ufile Ufile) {
		try {
			return ufileMapper.updateByPrimaryKey(Ufile) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddUfile(Ufile ufile) {
		try {
			return ufileMapper.insert(ufile) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int GetUfileCount(boolean includepri, int userid) {
		try {
			UfileExample ufileExample = new UfileExample();
			if(includepri) ufileExample.or().andUserIdEqualTo(userid);
			else ufileExample.or().andUserIdEqualTo(userid).andPriEqualTo(0);
			return ufileMapper.countByExample(ufileExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetUfileCount(boolean includepri) {
		try {
			UfileExample ufileExample = new UfileExample();
			if(!includepri)  ufileExample.or().andPriEqualTo(0);
			return ufileMapper.countByExample(ufileExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



}
