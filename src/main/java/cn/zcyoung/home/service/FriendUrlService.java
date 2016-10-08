package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.pojo.User;

public interface FriendUrlService {
	public static int By_CLICK = 1;
	public static int By_TIME = 2;
	//增
	public boolean AddFriendUrl(Friendurl friendurl);
	//删
	public boolean DeleteFirendUrl(int id);
	//改
	public boolean UpdateFriendUrl(Friendurl friendurl);
	//查
	public Friendurl GetFriendUrlById(int id);
	public List<Friendurl> GetListFriendUrl(int type, boolean includeuncheck);
	public List<Friendurl> GetListFriendUrl(int type, boolean includeuncheck, int PageIndex, int PageSize);
	public List<Friendurl> GetListFriendUrl(int type, boolean includeuncheck, int PageIndex, int PageSize, int userid);
	//审核
	public boolean CheckFreind(User user, int id);
	//数量
	public int GetFriendUrlCount(int type, boolean includeuncheck);
	public int GetFriendUrlCount(int type, boolean includeuncheck, int userid);
}
