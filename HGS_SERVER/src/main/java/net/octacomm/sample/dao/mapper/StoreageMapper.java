package net.octacomm.sample.dao.mapper;

import java.util.List;
import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.Customer;
import net.octacomm.sample.domain.Storeage;
import net.octacomm.sample.domain.StoreageParam;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@CacheNamespace
public abstract interface StoreageMapper extends CRUDMapper<Storeage, StoreageParam, Integer>
{
  public static final String INSERT_FIELDS = " ( id, sms, ctmIdx, modelName, storeageSn, productSpec, installDate, installArea, stgIp, pIdx, createDate )";
  public static final String INSERT_VALUES = " ( null, #{sms} , #{ctmIdx}, #{modelName}, #{storeageSn}, #{productSpec}, #{installDate}, #{installArea}, #{stgIp}, #{pIdx}, now() )";
  public static final String TABLE_NAME = " tb_storeage ";
  public static final String UPDATE_VALUES = "ctmIdx = #{ctmIdx} , modelName = #{modelName} , storeageSn = #{storeageSn},  productSpec = #{productSpec} , installDate = #{installDate}, installArea = #{installArea}, stgIp = #{stgIp} ";
  public static final String SELECT_FIELDS = "  id, sms, ctmIdx, modelName, storeageSn, storeageName, productSpec, installDate, installArea, stgIp, pIdx, createDate ";

  @Insert({"INSERT INTO  tb_storeage   ( id, sms, ctmIdx, modelName, storeageSn, productSpec, installDate, installArea, stgIp, pIdx, createDate ) VALUES  ( null, #{sms} , #{ctmIdx}, #{modelName}, #{storeageSn}, #{productSpec}, #{installDate}, #{installArea}, #{stgIp}, #{pIdx}, now() )"})
  public abstract int insert(Storeage paramStoreage);

  @Select({"SELECT * FROM  tb_storeage "})
  public abstract List<Storeage> getList();

  @Delete({"DELETE FROM  tb_storeage  WHERE id = #{id}"})
  public abstract int delete(Integer paramInteger);

  @Update({"UPDATE  tb_storeage  SET ctmIdx = #{ctmIdx} , modelName = #{modelName} , storeageSn = #{storeageSn},  productSpec = #{productSpec} , installDate = #{installDate}, installArea = #{installArea}, stgIp = #{stgIp}  WHERE id =  #{id}"})
  public abstract int update(Storeage paramStoreage);

  @Select({"SELECT * FROM  tb_storeage  WHERE id =  #{id}"})
  public abstract Storeage get(Integer paramInteger);

  @Update({"UPDATE  tb_storeage  SET sms = #{sms}  WHERE id =  #{id}"})
  public abstract int changeSmsStatus(Storeage paramStoreage);

  @Update({"update tb_storeage set sms = 1"})
  public abstract void resetSmsStatus();

  @Select({"select tb_storeage.sms from tb_customer, tb_storeage where tb_customer.id = tb_storeage.ctmIdx and managerHP = #{managerHP}"})
  public abstract Integer getStorageInfo(Customer paramCustomer);

  @Delete({"DELETE FROM  tb_storeage  WHERE ctmIdx = #{id}"})
  public abstract void deleteParent(int paramInt);

  @Select({"SELECT * FROM  tb_storeage  WHERE modelName = #{modelName} and stgIp = #{stgIp}"})
  public abstract Storeage doCheckDuplication(Storeage paramStoreage);
}