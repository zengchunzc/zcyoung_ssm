package cn.zcyoung.home.service;

import cn.zcyoung.home.pojo.User;

public interface UserService {
	public User getUserById(int userId);
	public int getUserCount();
}
