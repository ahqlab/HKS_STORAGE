package com.hankisul.storage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hankisul.storage.dao.CRUDMapper;
import com.hankisul.storage.domain.User;
import com.hankisul.storage.domain.UserParam;

@CacheNamespace
public abstract interface UserMapper extends CRUDMapper<User, UserParam, Integer>
{
  public static final String INSERT_FIELDS = " ( id, userId, password, role, roleKorNm, company, serviceEmail, serviceEmailPassword, createDate )";
  public static final String INSERT_VALUES = " ( null, #{userId}, #{password}, #{role}, #{roleKorNm}, #{company},  #{serviceEmail}, #{serviceEmailPassword},  now() )";
  public static final String TABLE_NAME = " tb_user ";
  public static final String UPDATE_VALUES = " userId = #{userId} , role = #{role}, roleKorNm = #{roleKorNm} , company = #{company}, serviceEmail = #{serviceEmail}, serviceEmailPassword = #{serviceEmailPassword} ";
  public static final String UPDATE_PASSWORD_VALUES = " password = #{newPassword1} ";
  public static final String SELECT_FIELDS = " id, userId, password, role, roleKorNm, company, serviceEmail, serviceEmailPassword, createDate ";

  @Insert({"INSERT INTO  tb_user   ( id, userId, password, role, roleKorNm, company, serviceEmail, serviceEmailPassword, createDate ) VALUES  ( null, #{userId}, #{password}, #{role}, #{roleKorNm}, #{company},  #{serviceEmail}, #{serviceEmailPassword},  now() )"})
  public abstract int insert(User paramUser);

  @Select({"SELECT * FROM  tb_user "})
  public abstract List<User> getList();

  @Delete({"DELETE FROM  tb_user  WHERE id =  #{id}"})
  public abstract int delete(Integer paramInteger);

  @Update({"UPDATE  tb_user  SET  userId = #{userId} , role = #{role}, roleKorNm = #{roleKorNm} , company = #{company}, serviceEmail = #{serviceEmail}, serviceEmailPassword = #{serviceEmailPassword}  WHERE id =  #{id}"})
  public abstract int update(User paramUser);

  @Select({"SELECT * FROM  tb_user  WHERE id =  #{id}"})
  public abstract User get(Integer paramInteger);

  @Select({"SELECT * FROM  tb_user  WHERE userId = #{userId}"})
  public abstract User getUser(User paramUser);

  @Select({"SELECT * FROM  tb_user   WHERE userId = #{userId} AND password = #{password} "})
  public abstract User getUserForAuth(User paramUser);

  @Update({"UPDATE  tb_user  SET  password = #{newPassword1}  WHERE id =  #{id}"})
  public abstract int changeUserPassword(User paramUser);

  @Update({"UPDATE tb_user SET password = #{password} WHERE id =  #{id}"})
  public abstract int changeAdminUserPassword(@Param("id") int paramInt, @Param("password") String paramString);

  public abstract int regist(User paramUser);
}