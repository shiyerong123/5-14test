package com.jk.mapper;


import com.jk.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Insert("insert into t_user (login_number,password) values(#{loginNumber},#{password})")
    void reg(User user);
    @Select("select count(*) from t_user where login_number=#{loginNumber}")
    long findUserName(String loginNumber);
}
