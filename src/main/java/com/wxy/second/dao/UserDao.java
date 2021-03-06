package com.wxy.second.dao;


import com.wxy.second.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserDao {
	
	@Select("select * from `miaosha_user` where id = #{id}")
	User getById(@Param("id") int id);

	@Insert("insert into `miaosha_user`(id, name)values(#{id}, #{name})")
	int insert(User user);
	
}
