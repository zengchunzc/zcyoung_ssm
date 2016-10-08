package cn.zcyoung.home.utils;

public class GoForward {
	private String data;
	private String time;
	private String url;
	
	public GoForward(){
		data = "参数有误， 3s后条跳转回首页。";
		time = "3000";
		url = "/view/index";
	}
	public GoForward(String data, String time, String url){
		this.data = data;
		this.time = time;
		this.url = url;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
