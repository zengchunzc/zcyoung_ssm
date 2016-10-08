package cn.zcyoung.home.service;

import java.util.List;


import cn.zcyoung.home.pojo.User;

public interface UserService {
	//根据Id找成员
	public User GetUserById(int id);
	public User GetUserByUsername(String username);
	//获取全部成员
	public List<User> GetListUser();
	public List<User> GetListUser(int PageIndex, int PageSize);
	public List<User> GetListUser(int PageIndex, int PageSize, String key);
	//获取管理员
	public List<User> GetListAdmin();
	//删除成员
	public boolean DeleteUser(int id);
	//更新成员
	public boolean UpdateUser(User user);
	//增加成员
	public boolean AddUser(User user);
	//总会员数
	public int GetUserCount();
	public int GetUserCount(String key);
	public int GetUserFriendCount(int userid);
	//获取好友列表
	public List<User> GetFriendList(int id);
	public List<User> GetFriendList(int id, int PageIndex, int PageSize);
	//获取暂定好友列表
	public List<User> GetTmpFriendList(int id);
	//登录
	public User Login(String username, String password);
	//添加好友
	public boolean AddFriend(User user, User friend);
	//审核好友
	public boolean CheckFriend(User user, User firend);
	//删除好友
	public boolean DeleteFriendId(User user, int fid);
	public boolean DeleteTmpFriendId(User user, int fid);
}
