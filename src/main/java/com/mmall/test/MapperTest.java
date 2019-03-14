package com.mmall.test;

import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testCRUD() {
     User user= userMapper.selectByPrimaryKey(1);
     if (user!=null){
         System.out.println(user.getEmail());
     }else{
         System.out.println("凉凉");
     }
    }
}
