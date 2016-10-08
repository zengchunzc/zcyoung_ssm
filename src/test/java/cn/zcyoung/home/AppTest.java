package cn.zcyoung.home;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zcyoung.home.dao.NewsMapper;
import cn.zcyoung.home.dao.UserMapper;
import cn.zcyoung.home.pojo.NewsExample;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.pojo.UserExample;
import cn.zcyoung.home.service.NewsService;
import cn.zcyoung.home.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class AppTest {
	@Resource
	private UserService userService = null;
	@Resource
	private UserMapper userMapper = null;
	@Resource 
	private NewsMapper newsMapper = null;
	@Resource
	private NewsService newsService;
	@Test
	public void Test1(){
		try{
			System.out.println(newsService.GetNewsCount());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}