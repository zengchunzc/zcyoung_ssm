package cn.zcyoung.home.web.listener;


import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.zcyoung.home.pojo.News;
import cn.zcyoung.home.service.NewsService;
import cn.zcyoung.home.utils.NewsUtils;

@Service
public class startListener implements ServletContextListener{

	private NewsService newsService;//////mapper 没有注入，怎么办

	private Thread myThread;  

	public void contextDestroyed(ServletContextEvent e) {  
		if (myThread != null && myThread.isInterrupted()) {  
			myThread.interrupt();  
		}  
	}  

	public void contextInitialized(ServletContextEvent e) {  
		if (myThread == null) {  
			newsService = WebApplicationContextUtils.getWebApplicationContext(e.getServletContext()).getBean(NewsService.class);
			myThread = new MyThread();  
			myThread.start();
		}  
	}  
	class MyThread extends Thread {  
		public void run() {  
			while(true){
				try {
					List<News> list=NewsUtils.getListNews();
					int len = list.size();
					for(int j = len - 1; j >= 0; j--){
						News n = list.get(j);
						if(n == null) continue;
						News ss = newsService.GetNewsByUrl(n.getUrl());
						if(ss == null){
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
			/*
			for(int i = 1363; i >= 1; i--){
				System.out.println(i);
				try {
					List<News> list=NewsUtils.getListNews(i);
					int len = list.size();
					for(int j = len - 1; j >= 0; j--){
						News n = list.get(j);
						if(n == null) continue;
						News ss = newsService.GetNewsByUrl(n.getUrl());
						if(ss == null){
							newsService.AddNews(n);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}*/
		}  
	} 
}

