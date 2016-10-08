package cn.zcyoung.home.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QiandaoUtils {

	public static String PostSendHttp(String url,String param) {
		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
            out.print(e.toString());
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
	}
	public static String GetSendHttp(String url,String random,List<String> cookies) {
		String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "None");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
            if(cookies != null){
            	connection.addRequestProperty("Cookie", cookies.get(0).split(";")[0]+";"+random+"=0");
    		}else {
    			connection.addRequestProperty("Cookie", random+"=0");
			}
            // 建立实际的连接
            connection.connect();
            
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
	}
	
	public static List<String> GetCookies(String url,String random) {
		String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "None");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
            connection.addRequestProperty("Cookie", random+"=0");
            // 建立实际的连接
            connection.connect();
            
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            List<String> cookieList = connection.getHeaderFields().get("Set-Cookie");
            return cookieList;
        } catch (Exception e) {
            System.out.println("取cookie出现异常！" + e + result);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
	}
	/**
	 * 判断用户是否已签到
	 * 
	 * @return 签到代码 0 未签到 1 已签到 2 非法用户
	 */
	@SuppressWarnings("deprecation")
	public int CheckNo(String phonenumber) {
		String random =""+ Math.random();
		int nowday, nowmonth, lastday, lastmonth;
		String json = GetSendHttp("http://111.8.20.252/waps/signPolite/getUserInfo?serialNumber="+phonenumber+"&pageName=SignPolite.html&ajaxSubmitType=post&ajax_randomcode="+random,random,null);
		long max = 0;
		String now = "";
		Pattern pattern = Pattern.compile("(?<=signTime\":)([0-9]*)(?=,)");
		Matcher match = pattern.matcher(json);
		while (match.find()) {
			now = match.group(0);
			if (now != "") {
				if (Long.parseLong(now) > max)
					max = Long.parseLong(now);
			}
		}
		if (now == "") {
			if (json.indexOf("\"isSchoolUser\":\"true\"") == -1)
				return 2;
			else {
				return 0;
			}
		}
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(max);
		nowday = calendar.get(Calendar.DAY_OF_MONTH);
		nowmonth = calendar.get(Calendar.MONTH);
		lastday = date.getDate();
		lastmonth = date.getMonth();
		if (nowmonth == lastmonth && nowday > lastday) {
			return 0;
		} else if (nowmonth > lastmonth) {
			return 0;
		} else {
			return 1;
		}

	}

	public boolean Qiandao(String phonenumber) {
		String random1 =""+ Math.random();
		String random2 =""+ Math.random();
		String json = GetSendHttp("http://111.8.20.252/waps/signPolite/sign?undefined&pageName=SignPolite.html&ajaxSubmitType=post&ajax_randomcode="+random2,random2,GetCookies("http://111.8.20.252/waps/signPolite/getUserInfo?serialNumber="+phonenumber+"&pageName=SignPolite.html&ajaxSubmitType=post&ajax_randomcode="+random1,random1));
		if (json.indexOf("X_RESULTCODE\":\"0") != -1) {
			return true;
		}
		return false;
		
	}
}
