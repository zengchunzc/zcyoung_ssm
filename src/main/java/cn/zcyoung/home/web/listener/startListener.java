package cn.zcyoung.home.web.listener;


import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.zcyoung.home.pojo.News;
import cn.zcyoung.home.pojo.Ufile;
import cn.zcyoung.home.service.NewsService;
import cn.zcyoung.home.service.UfileService;
import cn.zcyoung.home.utils.NewsUtils;

@Service
public class startListener implements ServletContextListener{

	private NewsService newsService;
	private UfileService ufileService;
	private Thread myThread, cleanThread;
	
	private ServletContext servletContext;

	public void contextDestroyed(ServletContextEvent e) {  
		if (myThread != null && myThread.isInterrupted()) {  
			myThread.interrupt();  
		}  
		if (cleanThread != null && cleanThread.isInterrupted()){
			cleanThread.interrupt();
		}
	}  

	public void contextInitialized(ServletContextEvent e) {  
		if (myThread == null) {  
			newsService = WebApplicationContextUtils.getWebApplicationContext(e.getServletContext()).getBean(NewsService.class);
			servletContext = WebApplicationContextUtils.getWebApplicationContext(e.getServletContext()).getBean(ServletContext.class);
			ufileService = WebApplicationContextUtils.getWebApplicationContext(e.getServletContext()).getBean(UfileService.class);
			myThread = new MyThread();  
			myThread.start();
			cleanThread = new CleanThread();
			cleanThread.start();
		}  
	}  
	
	//定期清理body线程
	class CleanThread extends Thread{
		public void run(){
			
			while(true){
				try {
					int count = newsService.GetNotNullBodyCount();
					//只存储点击量前3000的新闻,超过4000清理一次
					int maxN = 4000;
					int delN = 1000;
					if(count > maxN){
						int num = delN > (count - maxN) ? delN : (count - maxN);
						List<News> list = newsService.GetListNews(1, num, true);
						for(News news : list){
							news.setBody("");
							newsService.UpdateNews(news);
						}
						System.out.println("共清理掉" + num + "份记录");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						Thread.sleep(1000*60*60*24);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}//每天刷新一次
				}
			}
		}
	}
	
	//下载新闻线程
	class MyThread extends Thread {  
		public void run() {  
			String path = servletContext.getRealPath("/") + "upload/image/topic/";
			File f= new File(path);
			if(!f.exists()) f.mkdirs();
			
			while(true){
				try {
					List<News> list = NewsUtils.getListNews();
					int len = list.size();
					for(int j = len - 1; j >= 0; j--){
						News n = list.get(j);
						if(n == null) continue;
						News ss = newsService.GetNewsByUrl(n.getUrl());
						if(ss == null){
							if(n.getImg() != null && !n.getImg().equals("")){
								int index = n.getImg().lastIndexOf("/");
								String name = n.getImg().substring(index + 1);
								String address = "/upload/image/topic/" + name;
								Ufile uf = ufileService.GetUfileByAddress(address);
								if(uf != null && uf.getAddress() != null){
									n.setImg(address);
								}else{
									Ufile ufile = new Ufile();
									ufile.setAddress(address);
									ufile.setPri(1);
									ufile.setName(name);
									ufile.setTime(new Date());
									ufile.setType("img");
									ufile.setUserId(1);
									ufile.setSize(0l);
									if(ufileService.AddUfile(ufile) && NewsUtils.download("http://" + n.getImg().substring(0, index) + "/" + toUtf8String(name), name, path)){
										n.setImg(address);
									}
								}
							}
							newsService.AddNews(n);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						Thread.sleep(1000*60*60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}//60分钟刷一次
				}
			}
		}  
	} 
	
	public String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}

