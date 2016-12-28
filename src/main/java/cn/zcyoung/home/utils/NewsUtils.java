package cn.zcyoung.home.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import cn.zcyoung.home.pojo.News;

public class NewsUtils{

	public static News getNewsBodyByurl(String url){
		String t_body = "";
		String body = "";
		String sourse = "";
		News news = new News();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL u = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) u.openConnection();
			conn.setSSLSocketFactory(ssf);

			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			String s;
			while((s = br.readLine()) != null){
				t_body += s;
				t_body += "\r\n";
			}
			br.close();
			is.close();

			Pattern pname = Pattern.compile("(?<=<div id=\"news_body\">)([\\s\\S]{50,100000})(?=</div><!--end: news_body -->)");
			Matcher mname = pname.matcher(t_body);
			if(mname.find()){
				body = mname.group();
			}
			news.setBody(body);

			Pattern ps = Pattern.compile("(?<=<a id=\"link_source1\" class=\"wz\" target=\"_blank\" href=\")([\\s\\S]{10,200})(?=\">原文链接</a>)");
			Matcher psm = ps.matcher(t_body);
			if(psm.find()){
				sourse = psm.group();
			}
			news.setSource(sourse);

		} catch (Exception e) {
			//e.printStackTrace();
		}
		return news;
	}

	public static List<News> getListNews(int page){
		List<News> news = new ArrayList<News>();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url=new URL("https://news.cnblogs.com/n/page/" + page);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			String body = "";
			String s;
			while((s = br.readLine())!=null){
				body += s;
				body += "\r\n";
			}
			br.close();
			is.close();
			String regex = "(?<=<h2 class=\"news_entry\">)([\\s\\S]{200,2000})(?=<!--end: entry_footer -->)";
			Pattern p = Pattern.compile(regex);

			Matcher m = p.matcher(body);
			while(m.find()){
				news.add(getNews(m.group()));
			}
			return news;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static List<News> getListNews(){
		List<News> news = new ArrayList<News>();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url=new URL("https://news.cnblogs.com");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			String body = "";
			String s;
			while((s = br.readLine())!=null){
				body += s;
				body += "\r\n";
			}
			br.close();
			is.close();
			String regex = "(?<=<h2 class=\"news_entry\">)([\\s\\S]{200,2000})(?=<!--end: entry_footer -->)";

			Pattern p = Pattern.compile(regex);

			Matcher m = p.matcher(body);
			while(m.find()){
				news.add(getNews(m.group()));
			}
			return news;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static News getNews(String body){
		String name = "";
		String body_pre = "";
		String url = "";
		String author = "";
		String sendtime = "";
		String img = "";

		Pattern pname = Pattern.compile("(?<=target=\"_blank\">)([\\s\\S]{3,50})(?=</a>)");
		Matcher mname = pname.matcher(body);
		if(mname.find()){
			name = mname.group();
		}

		Pattern pbody = Pattern.compile("(?<=<div class=\"entry_summary\" style=\"display: block;\">)([\\s\\S]{10,500})(?=</div>)");
		Matcher mbody = pbody.matcher(body);
		if(mbody.find()){
			String ans = mbody.group();
			int start = ans.indexOf("<a");
			int end = ans.indexOf("</a>");
			if(start > 0 && end > 0) ans = ans.substring(0, start) + ans.substring(end + 4);
			body_pre = ans;
		}

		Pattern purl = Pattern.compile("(?<=<a href=\")([\\s\\S]{1,50})(?=\" target=\"_blank\")");
		Matcher murl = purl.matcher(body);
		if(murl.find()){
			url = "https://news.cnblogs.com" + murl.group();
		}

		Pattern ptime = Pattern.compile("(?<=发布于 <span class=\"gray\">)([\\s\\S]{10,30})(?=</span>)");
		Matcher mtime = ptime.matcher(body);
		if(mtime.find()){
			sendtime = mtime.group();
		}

		Pattern pzuozhe = Pattern.compile("(?<=\"gray\">)([\\s\\S]{1,20})(?=</a>)");
		Matcher mzuozhe = pzuozhe.matcher(body);
		if(mzuozhe.find()){
			author = mzuozhe.group();
		}
		
		Pattern pimg = Pattern.compile("(?<=src=\"//)([\\s\\S]{1,200}(.png|.jpg|.gif))");
		Matcher mcimg = pimg.matcher(body);
		if(mcimg.find()){
			img = mcimg.group();
		}
		
		News news = new News();
		news.setAuthor(author.trim());
		news.setBodyPre(body_pre.trim());
		news.setUrl(url.trim());
		news.setName(name.trim());
		news.setImg(img);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");                
		try {
			Date date = sdf.parse(sendtime);
			news.setSendtime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return news;
	}
	
	public static boolean download(String urlStr, String fileName,String savePath){ 
		try {
			URL url = new URL(urlStr);    
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
	        conn.setConnectTimeout(3*1000);  
	        //防止屏蔽程序抓取而返回403错误  
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
	        //得到输入流  
	        InputStream inputStream = conn.getInputStream();    
	        //获取自己数组  
	        byte[] buffer = new byte[1024];    
	        int len = 0;    
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	        while((len = inputStream.read(buffer)) != -1) {    
	            bos.write(buffer, 0, len);    
	        }    
	        bos.close();
	        byte[] getData = bos.toByteArray();
	  
	        //文件保存位置  
	        File saveDir = new File(savePath);  
	        if(!saveDir.exists()){  
	            saveDir.mkdir();  
	        }  
	        File file = new File(saveDir, fileName);
	        FileOutputStream fos = new FileOutputStream(file);       
	        fos.write(getData);   
	        if(fos!=null){  
	            fos.close();    
	        }  
	        if(inputStream!=null){  
	            inputStream.close();  
	        }  
	        return true;
		} catch (Exception e) {
		}
		return false;
        
    }  
  
}
