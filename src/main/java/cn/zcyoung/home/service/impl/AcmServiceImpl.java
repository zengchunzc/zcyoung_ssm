package cn.zcyoung.home.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import cn.zcyoung.home.dao.AcmlogMapper;
import cn.zcyoung.home.dao.UserMapper;
import cn.zcyoung.home.pojo.Acmlog;
import cn.zcyoung.home.pojo.AcmlogExample;
import cn.zcyoung.home.service.AcmService;
import cn.zcyoung.home.utils.AcmRankUtils;
import cn.zcyoung.home.utils.AcmStudent;

@Service
public class AcmServiceImpl implements AcmService{

	@Resource
	private AcmlogMapper acmlogMapper;
	@Resource
	private UserMapper userMapper;

	@Override
	public List<Acmlog> GetListAcmLog() {
		try {
			AcmlogExample acmlogExample = new AcmlogExample();
			acmlogExample.setOrderByClause("time desc");
			return acmlogMapper.selectByExample(acmlogExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Acmlog> GetListAcmLog(String no, boolean ismonth) {
		try {
			AcmlogExample acmlogExample = new AcmlogExample();
			acmlogExample.setOrderByClause("time desc");
			if(ismonth)acmlogExample.or().andStateEqualTo(0).andNoEqualTo(no);
			else acmlogExample.or().andNoEqualTo(no);
			return acmlogMapper.selectByExample(acmlogExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int GetMonthScore(String no) {
		try {
			List<Acmlog> list = GetListAcmLog(no, true);
			int score = 0;
			for(Acmlog al : list)
				score += al.getScore();
			return score;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetYearScore(String no) {
		try {
			List<Acmlog> list = GetListAcmLog(no, false);
			int score = 0;
			for(Acmlog al : list)
				score += al.getScore();
			return score;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean DeleteByContestName(String contestname) {
		try {
			AcmlogExample acmlogExample = new AcmlogExample();
			acmlogExample.or().andContestnameEqualTo(contestname);
			return acmlogMapper.deleteByExample(acmlogExample) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddContest(String title, String html) {
		try {
			int len = AcmRankUtils.getQesCount(html);
			List<AcmStudent> listStudent = AcmRankUtils.getListStudent(html, len);
			//规则1
			for(AcmStudent as : listStudent){
				if(as.getACount() > 0){
					Acmlog acmlog = new Acmlog();
					acmlog.setContestname(title);
					acmlog.setScore(10 * as.getACount());
					acmlog.setState(0);
					acmlog.setTime(new Date());
					acmlog.setNo(as.getNo());
					acmlog.setMess("在[" + title + "]中,解决了" + as.getACount() + "题,"+ acmlog.getScore() + "分");
					AddAcmLog(acmlog);
				}
			}
			//规则2
			for(int zm = 0; zm < len; zm++){
				String no = "0";
				int min = 0x3f3f3f3f, t;
				for(AcmStudent as : listStudent){
					String at = as.getAtime()[zm];
					if(at != null && at.contains(":")){
						String[] ss = at.split(":");
						t = Integer.parseInt(ss[0]) * 3600 + Integer.parseInt(ss[1]) * 60 + Integer.parseInt(ss[2]);
						if(t < min) {min = t; no = as.getNo();}
					}
				}
				if(!no.equals("0")){
					Acmlog acmlog = new Acmlog();
					acmlog.setContestname(title);
					acmlog.setScore(2);
					acmlog.setState(0);
					acmlog.setTime(new Date());
					acmlog.setNo(no);
					acmlog.setMess("在[" + title + "]中,1A了" + (char)(zm + 'A') + "题,"+ acmlog.getScore() + "分");
					AddAcmLog(acmlog);
				}
			}

			//积分3/4/5
			if(listStudent.size() > 0){
				Acmlog acmlog = new Acmlog();
				acmlog.setContestname(title);
				acmlog.setScore(6);
				acmlog.setState(0);
				acmlog.setTime(new Date());
				acmlog.setNo(listStudent.get(0).getNo());
				acmlog.setMess("在[" + title + "]中,取得了第一名,"+ acmlog.getScore() + "分");
				AddAcmLog(acmlog);
			}
			if(listStudent.size() > 1){
				Acmlog acmlog = new Acmlog();
				acmlog.setContestname(title);
				acmlog.setScore(3);
				acmlog.setState(0);
				acmlog.setTime(new Date());
				acmlog.setNo(listStudent.get(1).getNo());
				acmlog.setMess("在[" + title + "]中,取得了第二名,"+ acmlog.getScore() + "分");
				AddAcmLog(acmlog);
			}
			
			if(listStudent.size() > 2){
				Acmlog acmlog = new Acmlog();
				acmlog.setContestname(title);
				acmlog.setScore(2);
				acmlog.setState(0);
				acmlog.setTime(new Date());
				acmlog.setNo(listStudent.get(2).getNo());
				acmlog.setMess("在[" + title + "]中,取得了第三名,"+ acmlog.getScore() + "分");
				AddAcmLog(acmlog);
			}
			
			//规则6
			for(AcmStudent as : listStudent){
				if(as.getACount() == len) {
					Acmlog acmlog = new Acmlog();
					acmlog.setContestname(title);
					acmlog.setScore(3);
					acmlog.setState(0);
					acmlog.setTime(new Date());
					acmlog.setNo(as.getNo());
					acmlog.setMess("在[" + title + "]中,ac全部题目,"+ acmlog.getScore() + "分");
					AddAcmLog(acmlog);
				}
			}
			
			//规则7
			for(AcmStudent as : listStudent){
				boolean s = true;
				for(int i = 0; i < as.getEcount().length; i++){
					if(as.getEcount()[i] != 0) {s = false;break;}
				}
				if(s){
					Acmlog acmlog = new Acmlog();
					acmlog.setContestname(title);
					acmlog.setScore(2);
					acmlog.setState(0);
					acmlog.setTime(new Date());
					acmlog.setNo(as.getNo());
					acmlog.setMess("在[" + title + "]中,当次全部ac的题目中没有wa提交,"+ acmlog.getScore() + "分");
					AddAcmLog(acmlog);
				}
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddAcmLog(Acmlog acmlog) {
		try {
			return acmlogMapper.insert(acmlog) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateAcmLog(Acmlog acmlog) {
		try {
			return acmlogMapper.updateByPrimaryKey(acmlog) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean newmonth() {
		try {
			AcmlogExample acmlogExample = new AcmlogExample();
			acmlogExample.setOrderByClause("time desc");
			acmlogExample.or().andStateEqualTo(0);
			List<Acmlog> list = acmlogMapper.selectByExample(acmlogExample);
			for(Acmlog al : list){
				al.setState(1);
				UpdateAcmLog(al);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
