package cn.zcyoung.home.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.FriendurlMapper;
import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.pojo.FriendurlExample;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.FriendUrlService;
import cn.zcyoung.home.utils.Page;

@Service
public class FriendUrlServiceImpl implements FriendUrlService{
	@Resource
	private FriendurlMapper friendurlMapper;

	@Override
	public Friendurl GetFriendUrlById(int id) {
		try {
			return friendurlMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean AddFriendUrl(Friendurl friendurl) {
		try {
			return friendurlMapper.insert(friendurl) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteFirendUrl(int id) {
		try {
			return friendurlMapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateFriendUrl(Friendurl friendurl) {
		try {
			return friendurlMapper.updateByPrimaryKey(friendurl) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Friendurl> GetListFriendUrl(int type, boolean includeuncheck) {
		try {
			FriendurlExample friendurlExample = new FriendurlExample();
			if(!includeuncheck) friendurlExample.or().andCheckStateEqualTo(1);
			if(type == FriendUrlService.By_CLICK) friendurlExample.setOrderByClause("click desc");
			if(type == FriendUrlService.By_TIME) friendurlExample.setOrderByClause("time desc");
			return friendurlMapper.selectByExample(friendurlExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Friendurl> GetListFriendUrl(int type, boolean includeuncheck,
			int PageIndex, int PageSize) {
		try {
			FriendurlExample friendurlExample = new FriendurlExample();
			if(!includeuncheck) friendurlExample.or().andCheckStateEqualTo(1);
			if(type == FriendUrlService.By_CLICK) friendurlExample.setOrderByClause("click desc");
			if(type == FriendUrlService.By_TIME) friendurlExample.setOrderByClause("time desc");
			friendurlExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return friendurlMapper.selectByExample(friendurlExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Friendurl> GetListFriendUrl(int type, boolean includeuncheck,
			int PageIndex, int PageSize, int userid) {
		try {
			FriendurlExample friendurlExample = new FriendurlExample();
			friendurlExample.or().andUserIdEqualTo(userid);
			if(!includeuncheck) friendurlExample.or().andCheckStateEqualTo(1);
			if(type == FriendUrlService.By_CLICK) friendurlExample.setOrderByClause("click desc");
			if(type == FriendUrlService.By_TIME) friendurlExample.setOrderByClause("time desc");
			friendurlExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return friendurlMapper.selectByExample(friendurlExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean CheckFreind(User user, int id) {
		try {
			Friendurl friendurl = GetFriendUrlById(id);
			if(friendurl != null && friendurl.getCheckState() != 1){
				friendurl.setCheckState(1);
				friendurl.setCheckId(user.getId());
				friendurl.setCheckTime(new Date());
				return UpdateFriendUrl(friendurl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int GetFriendUrlCount(int type, boolean includeuncheck) {
		try{
			FriendurlExample friendurlExample = new FriendurlExample();
			if(!includeuncheck) friendurlExample.or().andCheckStateEqualTo(1);
			if(type == FriendUrlService.By_CLICK) friendurlExample.setOrderByClause("click desc");
			if(type == FriendUrlService.By_TIME) friendurlExample.setOrderByClause("time desc");
			return friendurlMapper.countByExample(friendurlExample);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetFriendUrlCount(int type, boolean includeuncheck, int userid) {
		try{
			FriendurlExample friendurlExample = new FriendurlExample();
			friendurlExample.or().andUserIdEqualTo(userid);
			if(!includeuncheck) friendurlExample.or().andCheckStateEqualTo(1);
			if(type == FriendUrlService.By_CLICK) friendurlExample.setOrderByClause("click desc");
			if(type == FriendUrlService.By_TIME) friendurlExample.setOrderByClause("time desc");
			return friendurlMapper.countByExample(friendurlExample);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

}
