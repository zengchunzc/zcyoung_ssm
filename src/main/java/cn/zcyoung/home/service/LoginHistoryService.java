package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.LoginHistory;;

public interface LoginHistoryService {

	//根据Id找
	public LoginHistory GetLoginHistoryById(int id);
	//获取全部
	public List<LoginHistory> GetListLoginHistory(int PageIndex, int PageSize);
	public List<LoginHistory> GetListLoginHistory(int PageIndex, int PageSize, int userid);
	//删除
	public boolean DeleteLoginHistory(int id);
	//更新
	public boolean UpdateLoginHistory(LoginHistory LoginHistory);
	//增加
	public boolean AddLoginHistory(LoginHistory LoginHistory);
	//总数
	public int GetLoginHistoryCount();
	public int GetLoginHistoryCount(int userid);
	
}

