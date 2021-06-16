package net.octacomm.sample.service;

import java.util.List;

import net.octacomm.sample.domain.Customer;

public interface CustomerService {

	public int insert(Customer paramCustomer);

	public List<Customer> getList();

	public List<Customer> getAllList(int paramInt);

	public int delete(Integer paramInteger);

	public int update(Customer paramCustomer);

	public Customer get(Integer paramInteger);

	public Customer getMagagerHP(String paramString1, String paramString2);

	public Customer doCheckDuplication(Customer paramCustomer);

}
