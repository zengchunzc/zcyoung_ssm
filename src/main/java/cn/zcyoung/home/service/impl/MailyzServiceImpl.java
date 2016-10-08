package cn.zcyoung.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.MailyzMapper;
import cn.zcyoung.home.pojo.Mailyz;
import cn.zcyoung.home.service.MailyzService;

@Service
public class MailyzServiceImpl implements MailyzService{
	@Resource
	private MailyzMapper mailyzMapper;
	
	@Override
	public Mailyz GetMailyzById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mailyz> GetListMailyz(int PageIndex, int PageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mailyz> GetListMailyz(int PageIndex, int PageSize, int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean DeleteMailyz(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UpdateMailyz(Mailyz Mailyz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean AddMailyz(Mailyz Mailyz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int GetMailyzCount(int userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetMailyzTodayCount(int userid) {
		try {
			return mailyzMapper.countTodayCount(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean CheckCode(int id, String code) {
		// TODO Auto-generated method stub
		return false;
	}

}
