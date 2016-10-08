package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.Ufile;;

public interface UfileService {

	//根据Id找
	public Ufile GetUfileById(int id);
	//根据路径找
	public Ufile GetUfileByAddress(String address);
	//获取全部
	public List<Ufile> GetListUfile(boolean includepri, int userid);
	public List<Ufile> GetListUfile(boolean includepri, int PageIndex, int PageSize);
	public List<Ufile> GetListUfile(boolean includepri, int PageIndex, int PageSize, int userid);
	//删除
	public boolean DeleteUfile(int id);
	//更新
	public boolean UpdateUfile(Ufile Ufile);
	//增加
	public boolean AddUfile(Ufile Ufile);
	//总数
	public int GetUfileCount(boolean includepri, int userid);
	public int GetUfileCount(boolean includepri);
}
