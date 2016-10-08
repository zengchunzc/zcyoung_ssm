package cn.zcyoung.home.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.UserMapper;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.pojo.UserExample;
import cn.zcyoung.home.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper UserMapper;
	@Override
	public User getUserById(int userId) {
		return this.UserMapper.selectByPrimaryKey(userId);
	}
	@Override
	public int getUserCount(){
		return this.UserMapper.countByExample(new UserExample());
	}

}
