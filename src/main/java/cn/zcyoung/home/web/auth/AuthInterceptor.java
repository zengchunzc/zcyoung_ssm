package cn.zcyoung.home.web.auth;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.zcyoung.home.pojo.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
			AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
			//没有声明需要权限,或者声明不验证权限
			if(authPassport == null || (authPassport.validate() == false && authPassport.isadmin()==false && authPassport.isuser()==false))
				return true;
			else{
				User user = (User) request.getSession().getAttribute("User");
				if(user!=null && authPassport.isuser()){
					if(user.getRole().equals("user")||user.getRole().equals("admin"))
						return true;
				}
				if(user!=null && authPassport.isadmin()){
					if(user.getRole().equals("admin"))
						return true;
				}
				
				Map<String, String[]> params = request.getParameterMap();  
		        String queryString = "";  
		        for (String key : params.keySet()) {  
		            String[] values = params.get(key);  
		            for (int i = 0; i < values.length; i++) {  
		                String value = values[i];  
		                queryString += key + "=" + value + "&";  
		            }  
		        }  
				
				//返回到登录界面
				response.sendRedirect("/view/login.do?fromUrl="+request.getRequestURL()+"?"+queryString);
				return false;
			}       
		}
		else
			return true;   
	}
}