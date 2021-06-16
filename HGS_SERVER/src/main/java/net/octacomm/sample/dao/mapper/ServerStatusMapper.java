package net.octacomm.sample.dao.mapper;

import java.util.List;
import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.DefaultParam;
import net.octacomm.sample.domain.Email;
import net.octacomm.sample.domain.ServerStatus;
import net.octacomm.sample.domain.User;
import net.octacomm.sample.domain.UserParam;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.poi.util.Beta;
import org.springframework.stereotype.Service;

@Service("serverStatusMapper")
@CacheNamespace
public abstract interface ServerStatusMapper extends CRUDMapper<ServerStatus, DefaultParam, Integer> {
	
	public static final String INSERT_FIELDS = " ( id, userIdx, threadIdx, status, lastModefiedDt )";
	
	public static final String INSERT_VALUES = " ( null, #{userIdx}, #{threadIdx}, #{status}, now() )";
	
	public static final String TABLE_NAME = " tb_server_status ";
	
	public static final String UPDATE_VALUES = " threadIdx = #{threadIdx} , status = #{status} , lastModefiedDt =  now() ";
	
	public static final String SELECT_FIELDS = " id, userIdx, threadIdx, status, lastModefiedDt  ";
	
	
	@Insert({"INSERT INTO  tb_server_status   ( id, userIdx, threadIdx, status, lastModefiedDt ) VALUES  ( null, #{userIdx}, #{threadIdx}, #{status}, now() )"})
	@Override
	int insert(ServerStatus domain);

	@Select({ "SELECT * FROM  tb_server_status  WHERE userIdx =  #{userIdx}" })
	@Override
	ServerStatus get(Integer userIdx);

	@Override
	int update(ServerStatus domain);
	
	
	@Delete("DELETE FROM tb_server_status WHERE userIdx =  #{userIdx}")
	int deleteOne(@Param("userIdx") int userIdx);

	@Override
	int getCountByParam(DefaultParam param);

	@Override
	List<ServerStatus> getListByParam(int startRow, int pageSize, DefaultParam param);
	
	@Select({ "SELECT * FROM  tb_server_status" })
	@Override
	List<ServerStatus> getList();
		
	@Update({" UPDATE tb_server_status SET status = #{status} , lastModefiedDt = now() WHERE userIdx =  #{userIdx} "})
	boolean set(int userIdx, int status);

	
	@Select({ "SELECT * FROM  tb_server_status  WHERE userIdx =  #{userIdx} and threadIdx = #{threadIdx}" })
	ServerStatus getStatus(@Param("userIdx") int userIdx, @Param("threadIdx") int threadIdx);
	
	@Delete("DELETE FROM tb_server_status")
	void deleteAll();	

}