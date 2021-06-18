package com.hankisul.storage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hankisul.storage.dao.CRUDMapper;
import com.hankisul.storage.domain.Customer;
import com.hankisul.storage.domain.CustomerParam;

@CacheNamespace
public abstract interface CustomerMapper extends CRUDMapper<Customer, CustomerParam, Integer> {
	
	
	public static final String INSERT_FIELDS = " ( id, customerName, email, managerName, managerHP, sendNumber, pIdx, createDate )";
	public static final String INSERT_VALUES = " ( null, #{customerName}, #{email}, #{managerName}, #{managerHP},  #{sendNumber}, #{pIdx}, now() )";
	public static final String TABLE_NAME = " tb_customer ";
	public static final String UPDATE_VALUES = " customerName = #{customerName} , email = #{email} , managerName = #{managerName} , managerHP = #{managerHP} , sendNumber = #{sendNumber}";
	public static final String SELECT_FIELDS = "  id, customerName, email, managerName, managerHP, sendNumber, pIdx, createDate  ";

	@Insert({
			"INSERT INTO  tb_customer   ( id, customerName, email, managerName, managerHP, sendNumber, pIdx, createDate ) VALUES  ( null, #{customerName}, #{email}, #{managerName}, #{managerHP},  #{sendNumber}, #{pIdx}, now() )" })
	public int insert(Customer paramCustomer);

	@Select({ "SELECT * FROM  tb_customer " })
	public List<Customer> getList();

	@Select({ "SELECT * FROM  tb_customer  where pIdx =  #{pIdx}" })
	public List<Customer> getAllList(@Param("pIdx") int paramInt);

	@Delete({ "DELETE FROM  tb_customer  WHERE id =  #{id}" })
	public int delete(Integer paramInteger);

	@Update({
			"UPDATE  tb_customer  SET  customerName = #{customerName} , email = #{email} , managerName = #{managerName} , managerHP = #{managerHP} , sendNumber = #{sendNumber} WHERE id =  #{id}" })
	public int update(Customer paramCustomer);

	@Select({ "SELECT * FROM  tb_customer  WHERE id =  #{id}" })
	public Customer get(Integer paramInteger);

	@Select({
			"select managerHP, sendNumber, sms from tb_storeage join tb_customer where tb_storeage.ctmIdx = tb_customer.id and  tb_storeage.modelName = #{modelName} AND tb_storeage.stgIp = #{ip}" })
	public Customer getMagagerHP(@Param("modelName") String paramString1, @Param("ip") String paramString2);

	@Select({ "SELECT * FROM  tb_customer  WHERE customerName =  #{customerName}" })
	public Customer doCheckDuplication(Customer paramCustomer);
}