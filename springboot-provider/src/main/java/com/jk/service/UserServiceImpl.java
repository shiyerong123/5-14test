package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.jk.mapper.UserMapper;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public String reg(User user) {
       long count = userMapper.findUserName(user.getLoginNumber());
       if(count>0){
           return "1";
       }else{
           userMapper.reg(user);
           return "2";
       }


    }


}
