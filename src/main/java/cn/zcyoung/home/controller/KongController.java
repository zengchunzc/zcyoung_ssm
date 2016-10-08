package cn.zcyoung.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zcyoung.home.utils.PjaxUtils;

@Controller
@RequestMapping("")
public class KongController {
	
	@RequestMapping("/2weima")
	public String to2weima(HttpServletRequest request){
		return PjaxUtils.get(request, "2weima");
	}
}
