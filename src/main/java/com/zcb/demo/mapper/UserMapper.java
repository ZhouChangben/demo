package com.zcb.demo.mapper;

import com.zcb.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(id,name,password) values (#{id},#{name},#{password})")
    void insert(User user);

    @Select("select * from user where name=#{name} and password=#{password}")
    User signin(@Param("name") String name, @Param("password") String password);

    @Select("select * from user where name=#{name}")
    User findByName(@Param("name")String name);
}
