package cn.zcyoung.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.pojo.Mailyz;

@Service
public interface MailyzService {

	//根据Id找
	public Mailyz GetMailyzById(int id);
	//获取全部
	public List<Mailyz> GetListMailyz(int PageIndex, int PageSize);
	public List<Mailyz> GetListMailyz(int PageIndex, int PageSize, int userid);
	//删除
	public boolean DeleteMailyz(int id);
	//更新
	public boolean UpdateMailyz(Mailyz Mailyz);
	//增加
	public boolean AddMailyz(Mailyz Mailyz);
	//总数
	public int GetMailyzCount(int userid);
	public int GetMailyzTodayCount(int userid);
	//验证code
	public boolean CheckCode(int id, String code);
	
	
}
