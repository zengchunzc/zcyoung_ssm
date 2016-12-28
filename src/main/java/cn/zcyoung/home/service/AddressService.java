package cn.zcyoung.home.service;

import java.util.List;

import cn.zcyoung.home.pojo.Address;

public interface AddressService {
	public boolean DeleteAddress(int id);
	public boolean DeleteAll(int userid);
	public boolean AddAddress(Address address);
	public List<Address> GetListAddress(int userid);
}
