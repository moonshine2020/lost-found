package com.nineteen.lostfound.service;

import com.nineteen.lostfound.entity.User;
import com.nineteen.lostfound.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mengxu on 2017/9/27.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public int login(User user){
      return userMapper.selectUser(user);
    }
}
