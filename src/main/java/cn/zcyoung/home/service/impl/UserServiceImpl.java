package cn.zcyoung.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.UserMapper;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.pojo.UserExample;
import cn.zcyoung.home.service.UserService;
import cn.zcyoung.home.utils.Page;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	@Override
	public User GetUserById(int id) {
		try {
			return userMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public User GetUserByUsername(String username) {
		try {
			UserExample userExample = new UserExample();
			userExample.or().andUsernameEqualTo(username);
			List<User> list = userMapper.selectByExample(userExample);
			return list != null && list.size() > 0 ? list.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> GetListUser() {
		try {
			return userMapper.selectByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> GetListUser(int PageIndex, int PageSize) {
		try {
			UserExample userExample = new UserExample();
			userExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize)); 
			userExample.setOrderByClause("r_time desc");
			return userMapper.selectByExample(userExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<User> GetListUser(int PageIndex, int PageSize, String key) {
		try {
			UserExample userExample = new UserExample();
			userExample.setOrderByClause("r_time desc");
			userExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize)); 
			userExample.or().andUsernameLike("%" + key + "%");
			return userMapper.selectByExample(userExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<User> GetListAdmin() {
		try {
			UserExample userExample = new UserExample();
			userExample.or().andRoleEqualTo("admin");
			return userMapper.selectByExample(userExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean DeleteUser(int id) {
		try {
			return userMapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateUser(User user) {
		try {
			return userMapper.updateByPrimaryKey(user) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddUser(User user) {
		try {
			return userMapper.insert(user) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int GetUserCount() {
		try {
			return userMapper.countByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetUserCount(String key) {
		try {
			UserExample userExample = new UserExample();
			userExample.or().andUsernameLike("%" + key + "%");
			return userMapper.countByExample(userExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<User> GetFriendList(int id) {
		try {
			String str = userMapper.selectByPrimaryKey(id).getFriendList();
			if(str == null || str.equals("")) return null;
			List<User> list = new ArrayList<User>();
			String[] su = str.split(";");
			for(String s : su){
				if(s == null || s.equals("")) continue;
				list.add(GetUserById(Integer.parseInt(s)));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> GetFriendList(int id, int start, int size) {
		List<User> friendlist = GetFriendList(id);
		if(friendlist == null) return null;
		List<User> list = new ArrayList<User>();
		for(int i = start; i < friendlist.size(); i++){
			list.add(friendlist.get(i));
			if(list.size() == size) break;
		}
		return list;
	}

	@Override
	public List<User> GetTmpFriendList(int id) {
		try {
			String str = userMapper.selectByPrimaryKey(id).getFriendListTmp();
			if(str == null || str.equals("")) return null;
			List<User> list = new ArrayList<User>();
			String[] su = str.split(";");
			for(String s : su){
				if(s == null || s.equals("")) continue;
				list.add(GetUserById(Integer.parseInt(s)));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User Login(String username, String password) {
		UserExample userExample = new UserExample();
		userExample.or().andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<User> list = userMapper.selectByExample(userExample);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public int GetUserFriendCount(int userid) {
		try {
			String str = userMapper.selectByPrimaryKey(userid).getFriendList();
			if(str == null || str.equals("")) return 0;
			List<User> list = new ArrayList<User>();
			String[] su = str.split(";");
			for(String s : su){
				if(s == null || s.equals("")) continue;
				list.add(GetUserById(Integer.parseInt(s)));
			}
			return list.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public boolean AddFriend(User a, User b) {
		if(a.getId()==b.getId()) return false;

		if(a.getFriendListTmp()==null)
			a.setFriendListTmp("");
		if(b.getFriendListTmp()==null)
			b.setFriendListTmp("");

		boolean flag=true;
		for(String m : a.getFriendList().split(";")){
			if(m.equals("")) continue;
			int tId=Integer.parseInt(m);
			if(tId==b.getId()){
				flag=false;
				break;
			}
		}
		if(!flag) return false;

		flag=true;
		for(String m:a.getFriendListTmp().split(";")){
			if(m.equals("")) continue;
			int tId=Integer.parseInt(m);
			if(tId==b.getId()){
				flag=false;
				break;
			}
		}
		if(!flag) return false;
		String friend_list_tmp=b.getFriendListTmp();
		friend_list_tmp+=a.getId()+";";
		b.setFriendListTmp(friend_list_tmp);
		return UpdateUser(b);
	}
	@Override
	public boolean CheckFriend(User a, User b) {
		boolean flag = false;//标记是否tmp中有这位好友
		if(a.getFriendListTmp() == null) a.setFriendListTmp("");
		if(b.getFriendListTmp() == null) b.setFriendListTmp("");
		String fl = "";
		for(String f : a.getFriendListTmp().split(";")){
			if(f == null || f.equals("")) continue;
			if(!f.equals("" + b.getId())) fl += f+";";
			if(f.equals("" + b.getId())) flag = true;
		}
		a.setFriendListTmp(fl);
		a.setFriendList(a.getFriendList()+b.getId()+";");
		b.setFriendList(b.getFriendList()+a.getId()+";");
		return flag && UpdateUser(b) && UpdateUser(a);
	}
	@Override
	public boolean DeleteFriendId(User user, int fid) {
		String friendlist = "";
		String[] str = user.getFriendList().split(";");
		for(int i = 0; i < str.length; i++)
			if(Integer.parseInt(str[i]) != fid) friendlist += str[i] + ";";
		user.setFriendList(friendlist);
		return UpdateUser(user);
	}
	@Override
	public boolean DeleteTmpFriendId(User user, int fid) {
		String friendlist = "";
		String[] str = user.getFriendListTmp().split(";");
		for(int i = 0; i < str.length; i++)
			if(Integer.parseInt(str[i]) != fid) friendlist += str[i] + ";";
		user.setFriendListTmp(friendlist);
		return UpdateUser(user);
	}



}
