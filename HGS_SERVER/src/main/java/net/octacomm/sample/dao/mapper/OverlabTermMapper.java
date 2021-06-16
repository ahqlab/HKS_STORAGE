package net.octacomm.sample.dao.mapper;

import java.util.List;
import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.OverlabTerm;
import net.octacomm.sample.domain.OverlabTermParam;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@CacheNamespace
public abstract interface OverlabTermMapper extends CRUDMapper<OverlabTerm, OverlabTermParam, Integer> {
	
	public static final String INSERT_FIELDS = " ( id, info, warning, error, pIdx )";
	
	public static final String INSERT_VALUES = " ( null, #{info}, #{warning}, #{error} , #{pIdx} )";
	
	public static final String TABLE_NAME = " tb_overlab_term ";
	
	public static final String UPDATE_VALUES = " info = #{info} , warning = #{warning} , error = #{error} ";
	
	public static final String SELECT_FIELDS = "  id, info, warning, error , pIdx";

	@Insert({
			"INSERT INTO  tb_overlab_term   ( id, info, warning, error, pIdx ) VALUES  ( null, #{info}, #{warning}, #{error} , #{pIdx} )" })
	public int insert(OverlabTerm paramOverlabTerm);

	@Select({ "SELECT * FROM  tb_overlab_term " })
	public List<OverlabTerm> getList();

	@Delete({ "DELETE FROM  tb_overlab_term  WHERE id =  #{id}" })
	public int delete(Integer paramInteger);

	@Update({
			"UPDATE  tb_overlab_term  SET  info = #{info} , warning = #{warning} , error = #{error}  WHERE id =  #{id}" })
	public int update(OverlabTerm paramOverlabTerm);

	@Select({ "SELECT * FROM  tb_overlab_term  WHERE id =  #{id}" })
	public OverlabTerm get(@Param("id") int id);

	@Select({ "SELECT * FROM  tb_overlab_term  WHERE pIdx =  #{pIdx}" })
	public OverlabTerm getObjOfPidx(@Param("pIdx") int paramInt);
}