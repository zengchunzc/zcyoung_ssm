package cn.zcyoung.home.utils;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;





import cn.zcyoung.home.pojo.Log;
import cn.zcyoung.home.pojo.Mailyz;
import cn.zcyoung.home.pojo.User;
import cn.zcyoung.home.service.LogService;
import cn.zcyoung.home.service.MailyzService;
import cn.zcyoung.home.utils.mail.SimpleMailSender;

@Component
public class MailUtils {
	
	@Resource
	private LogService logService;
	@Resource
	private MailyzService mailyzService;
	
	public boolean SendResetPw(User user, String pw){
		return SimpleMailSender.SendMail(user.getEmail(), user.getNickname() + ",管理员已经为你重置了登录密码,为【" + pw + "】，快登录<a href='http://home.zcyoung.cn'>zcyoung</a>进行修改吧。【zcyoung】", "来自zcyoung的密码重置通知");
	}
	
	public boolean SendMailCode(User user){
		int num = mailyzService.GetMailyzTodayCount(user.getId());
		if(num >= 5) return false;
		String code = "";
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rd = new Random();
		for(int i = 0; i < 15; i++){
			code += str.charAt(rd.nextInt(62));
		}
		if(SimpleMailSender.SendCodeMail(user.getEmail(), "http://home.zcyoung.cn/user/mailyz.do?code=" + code + "&uid=" + user.getId())){
			Mailyz my = new Mailyz();
			my.setUserId(user.getId());
			my.setCode(code);
			my.setTime(new Date());
			my.setTimelimit(1000 * 60 * 5L);
			mailyzService.AddMailyz(my);
			Log ml = new Log();
			ml.setUserId(user.getId());
			ml.setType("mail");
			ml.setData("验证码为：" + code);
			ml.setTime(new Date());
			logService.AddLog(ml);
			return true;
		}
		return false;
	}
}
