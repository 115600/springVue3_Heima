package org.wineky.spring_mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wineky.spring_mybatis.pojo.User;

@Mapper
public interface UserMapper {
    @Select("select * from User where id = #{id}")
    public User findById(Integer id);
}
