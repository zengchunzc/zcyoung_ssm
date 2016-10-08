package cn.zcyoung.home.utils;

import javax.servlet.http.HttpServletRequest;

public class PjaxUtils {
	public static String get(HttpServletRequest request, String str){
		if(request.getHeader("X-PJAX") != null) return "pjax/" + str;
		else return str;
	}
}
