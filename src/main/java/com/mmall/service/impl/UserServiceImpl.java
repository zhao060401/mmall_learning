package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse login(String username, String password) {
        int resultCount = userMapper.checkUserName(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorCodeMessage("用户名不存在");
        }
        //密码登陆MD5
        String MD5password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, MD5password);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage("密码错误");
        //登陆成功，把密码返回空，再传到前端
        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createBySuccessMessage("登陆成功", user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        int resultCount = userMapper.checkUserName(user.getUsername());
        if (resultCount > 0)
            return ServerResponse.createByErrorCodeMessage("用户名已经存在");
        int emailCont = userMapper.checkEmail(user.getEmail());
        if (emailCont > 0)
            return ServerResponse.createByErrorCodeMessage("此邮箱已注册");
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int insertCount = userMapper.insert(user);
        if (insertCount == 0)
            return ServerResponse.createByErrorMessage("系统异常，注册失败");
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String string, String type) {
        //type是非空的
        if (StringUtils.isBlank(type)) {
            if (type.equals(Const.EMAIL)) {
                //类型是邮箱
                int resultCount = userMapper.checkEmail(string);
                if (resultCount > 0)
                    return ServerResponse.createByErrorCodeMessage("用户名已经存在");
            }
            if (type.equals(Const.USERNAME)) {
                //类型是用户名
                int resultCount = userMapper.checkUserName(string);
                if (resultCount > 0)
                    return ServerResponse.createByErrorCodeMessage("用户名已经存在");
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        //相关的值不存在
        return ServerResponse.createBySuccess("校验成功");
    }

    @Override
    public ServerResponse<String> selectQuestion(String username) {
        ServerResponse<String> serverResponse = this.checkValid(username, Const.USERNAME);
        if (serverResponse.isSuccess()) {
            //用户不存在
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestionByName(username);
        if (StringUtils.isNoneBlank(question))
            return ServerResponse.createBySuccess(question);
        return ServerResponse.createByErrorMessage("密码找回问题为空");
    }

    @Override
    public ServerResponse<String> CheckAnswer(String username, String question, String answer) {
        int checkCount=userMapper.checkAnswer(username,question,answer);
        if (checkCount>0){
            //回答正确
            String forgetToken= UUID.randomUUID().toString();
        }
        return null;
    }


}
