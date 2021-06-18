package com.hankisul.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hankisul.storage.dao.mapper.CustomerMapper;
import com.hankisul.storage.domain.Customer;

@Component
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerMapper mapper;
	
	@Override
	public int insert(Customer paramCustomer) {
		return mapper.insert(paramCustomer);
	}

	@Override
	public List<Customer> getList() {
		return mapper.getList();
	}

	@Override
	public List<Customer> getAllList(int paramInt) {
		return mapper.getAllList(paramInt);
	}

	@Override
	public int delete(Integer paramInteger) {
		return mapper.delete(paramInteger);
	}

	@Override
	public int update(Customer paramCustomer) {
		return mapper.update(paramCustomer);
	}

	@Override
	public Customer get(Integer paramInteger) {
		return mapper.get(paramInteger);
	}

	@Override
	public Customer getMagagerHP(String paramString1, String paramString2) {
		return mapper.getMagagerHP(paramString1, paramString2);
	}

	@Override
	public Customer doCheckDuplication(Customer paramCustomer) {
		return mapper.doCheckDuplication(paramCustomer);
	}

}
