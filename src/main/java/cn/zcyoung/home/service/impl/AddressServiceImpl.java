package cn.zcyoung.home.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zcyoung.home.dao.AddressMapper;
import cn.zcyoung.home.pojo.Address;
import cn.zcyoung.home.pojo.AddressExample;
import cn.zcyoung.home.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Resource
	private AddressMapper addressMapper;
	@Override
	public boolean DeleteAddress(int id) {
		try {
			return addressMapper.deleteByPrimaryKey(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeleteAll(int userid) {
		try {
			AddressExample addressExample = new AddressExample();
			addressExample.or().andUserIdEqualTo(userid);
			return addressMapper.deleteByExample(addressExample) >= 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean AddAddress(Address address) {
		try {
			return addressMapper.insert(address) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Address> GetListAddress(int userid) {
		try {
			AddressExample addressExample = new AddressExample();
			addressExample.or().andUserIdEqualTo(userid);
			return addressMapper.selectByExample(addressExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
