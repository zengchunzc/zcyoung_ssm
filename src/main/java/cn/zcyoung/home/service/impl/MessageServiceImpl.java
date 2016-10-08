package cn.zcyoung.home.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.MessageMapper;
import cn.zcyoung.home.pojo.Message;
import cn.zcyoung.home.pojo.MessageExample;
import cn.zcyoung.home.service.MessageService;
import cn.zcyoung.home.utils.Page;

@Service
public class MessageServiceImpl implements MessageService{
	@Resource
	private MessageMapper messageMapper;
	
	@Override
	public Message GetMessageById(int id) {
		try {
			return messageMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Message> GetListMessage(int userid) {
		try {
			MessageExample messageExample = new MessageExample();
			messageExample.setOrderByClause("time desc");
			messageExample.or().andToIdEqualTo(userid);
			return messageMapper.selectByExample(messageExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Message> GetListMessage(int userid, int PageIndex, int PageSize) {
		try {
			MessageExample messageExample = new MessageExample();
			messageExample.setOrderByClause("time desc");
			messageExample.or().andToIdEqualTo(userid);
			messageExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			List<Message> lm = messageMapper.selectByExample(messageExample);
			for(Message ms : lm){
				ms.setState(1);
				UpdateMessage(ms);
			}
			return lm;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Message> GetListSendMessage(int userid) {
		try {
			MessageExample messageExample = new MessageExample();
			messageExample.setOrderByClause("time desc");
			messageExample.or().andFromIdEqualTo(userid);
			return messageMapper.selectByExample(messageExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Message> GetListSendMessage(int userid, int PageIndex,
			int PageSize) {
		try {
			MessageExample messageExample = new MessageExample();
			messageExample.setOrderByClause("time desc");
			messageExample.or().andFromIdEqualTo(userid);
			messageExample.setPage(new Page((PageIndex - 1) * PageSize, PageSize));
			return messageMapper.selectByExample(messageExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean DeleteMessage(int id) {
		try {
			return messageMapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateMessage(Message Message) {
		try {
			return messageMapper.updateByPrimaryKey(Message) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddMessage(Message message) {
		try {
			message.setTime(new Date());
			return messageMapper.insert(message) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int GetMessageCount() {
		try {
			return messageMapper.countByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetMessageCount(int userid) {
		try {
			MessageExample messageExample = new MessageExample();
			messageExample.or().andToIdEqualTo(userid);
			return messageMapper.countByExample(messageExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetMessageCount(int userid, boolean read) {
		try {
			MessageExample messageExample = new MessageExample();
			if(!read) messageExample.or().andToIdEqualTo(userid).andStateEqualTo(0);
			else messageExample.or().andToIdEqualTo(userid);
			return messageMapper.countByExample(messageExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int GetMessageSendCount(int userid) {
		try {
			MessageExample messageExample = new MessageExample();
			messageExample.or().andFromIdEqualTo(userid);
			return messageMapper.countByExample(messageExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean SendMessage(int id, int toid, String data, String url) {
		if(id == toid) return false;
		Message message = new Message();
		message.setData(data);
		message.setFromId(id);
		message.setToId(toid);
		message.setState(0);
		message.setUrl(url);
		return AddMessage(message);
	}

}
