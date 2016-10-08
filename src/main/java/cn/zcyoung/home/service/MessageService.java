package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.Message;


public interface MessageService {
	//根据Id找
	public Message GetMessageById(int id);
	//获取全部
	public List<Message> GetListMessage(int userid);
	public List<Message> GetListMessage(int userid, int PageIndex, int PageSize);
	public List<Message> GetListSendMessage(int userid);
	public List<Message> GetListSendMessage(int userid, int PageIndex, int PageSize);
	//删除
	public boolean DeleteMessage(int id);
	//更新
	public boolean UpdateMessage(Message Message);
	//增加
	public boolean AddMessage(Message Message);
	//总数
	public int GetMessageCount();
	public int GetMessageCount(int userid);
	public int GetMessageCount(int userid, boolean read);
	public int GetMessageSendCount(int userid);
	//发送
	public boolean SendMessage(int id, int toid, String data, String url);
 
}
