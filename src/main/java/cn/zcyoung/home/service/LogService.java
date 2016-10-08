package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.Log;

public interface LogService {

	//根据Id找
	public Log GetLogById(int id);
	//获取全部
	public List<Log> GetListLog(int PageIndex, int PageSize);
	public List<Log> GetListLog(int PageIndex, int PageSize, int userid);
	public List<Log> GetListLog(int PageIndex, int PageSize, String type);
	public List<Log> GetListLog(int PageIndex, int PageSize, int userid, String type);
	//删除
	public boolean DeleteLog(int id);
	//更新
	public boolean UpdateLog(Log log);
	//增加
	public boolean AddLog(Log log);
	//总数
	public int GetLogCount();
	public int GetLogCount(int userid);
	public int GetLogCount(int userid, String type);

}
