package com.hankisul.storage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hankisul.storage.dao.CRUDMapper;
import com.hankisul.storage.domain.Email;
import com.hankisul.storage.domain.EmailLogParam;

@CacheNamespace
public abstract interface EmailLogMapper extends CRUDMapper<Email, EmailLogParam, Integer> {
	
	public static final String INSERT_FIELDS = " ( id, status, eventName, storeageName, uId, ip, dateTime, mailFrom, mailDate, body )";
	
	public static final String INSERT_VALUES = " ( null,  #{status}, #{eventName}, #{storeageName}, #{uId}, #{ip}, #{dateTime}, #{mailFrom}, #{mailDate}, #{body} )";
	
	public static final String TABLE_NAME = " tb_email_log ";
	
	public static final String UPDATE_VALUES = " eventName = #{eventName} , storeageName = #{storeageName}, uId = #{uId} ,  ip = #{ip} , dateTime = #{dateTime}, mailFrom = #{mailFrom}, date = #{date}, body = #{body} ";
	
	public static final String SELECT_FIELDS = "  id, status, eventName, storeageName, uId, ip, dateTime, from, date, body ";

	public abstract int insert(Email paramEmail);

	@Select({ "SELECT * FROM  tb_email_log " })
	public List<Email> getList();

	@Delete({ "DELETE FROM  tb_email_log  WHERE id =  #{id}" })
	public int delete(Integer paramInteger);

	@Update({
			"UPDATE  tb_email_log  SET  eventName = #{eventName} , storeageName = #{storeageName}, uId = #{uId} ,  ip = #{ip} , dateTime = #{dateTime}, mailFrom = #{mailFrom}, date = #{date}, body = #{body}  WHERE id =  #{id}" })
	public int update(Email paramEmail);

	@Select({ "SELECT * FROM  tb_email_log  WHERE id =  #{id}" })
	public Email get(Integer paramInteger);

	@Select({ "SELECT COUNT(*) FROM  tb_email_log " })
	public int getCount();

	@Select({ "SELECT * FROM  tb_email_log  WHERE dateTime = #{dateTime}" })
	public List<Email> existDuplicateEmail(Email paramEmail);

	@Update({ "UPDATE  tb_email_log  SET sendMMS = 1 WHERE id =  #{id}" })
	public int checkSendMMS(@Param("id") int paramInt);

	@Select({ "select * from   tb_email_log   order by id desc limit 1" })
	public Email getListRow();
}