package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.Acmlog;


public interface AcmService {
	public boolean AddAcmLog(Acmlog acmlog);
	public boolean UpdateAcmLog(Acmlog acmlog);
	public List<Acmlog> GetListAcmLog();
	public List<Acmlog> GetListAcmLog(String no, boolean ismonth);
	public boolean DeleteByContestName(String contestname);
	public int GetMonthScore(String no);
	public int GetYearScore(String no);
	public boolean AddContest(String title, String html);
	public boolean newmonth();
}
