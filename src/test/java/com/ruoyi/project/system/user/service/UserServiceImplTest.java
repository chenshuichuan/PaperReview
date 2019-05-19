package com.ruoyi.project.system.user.service;

import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
  @Autowired
  private IUserService userService;

  @Autowired
  private UserMapper userMapper;
  @Test
  public void registerUser() {
  }
  @Test
  public void resetPassw(){
    User user = userService.selectUserByLoginName("admin");
    if(user != null){
      user.setPassword("admin123");
      userService.resetUserPwd(user);
    }
  }
}