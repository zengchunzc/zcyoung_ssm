package cn.zcyoung.home.utils;

import java.util.List;

import cn.zcyoung.home.pojo.Acmlog;
import cn.zcyoung.home.pojo.User;

public class PAcm  implements Comparable<PAcm>{

	private User user;
	private int month;
	private int year;
	private List<Acmlog> list;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<Acmlog> getList() {
		return list;
	}
	public void setList(List<Acmlog> list) {
		this.list = list;
	}
	@Override
	public int compareTo(PAcm o) {
		return  o.month - this.month;
	}
	
}
