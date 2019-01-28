package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
        if (StringUtils.isNotBlank(type)) {
            if (type.equals(Const.EMAIL)) {
                //类型是邮箱
                int resultCount = userMapper.checkEmail(string);
                if (resultCount > 0)
                    return ServerResponse.createByErrorCodeMessage("邮箱已经存在");
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
        int checkCount = userMapper.checkAnswer(username, question, answer);
        if (checkCount > 0) {
            //回答正确
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }

    @Override
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken))
            return ServerResponse.createByErrorMessage("参数错误，需要传递Token值");
        ServerResponse<String> serverResponse = this.checkValid(username, Const.USERNAME);
        if (serverResponse.isSuccess()) {
            //用户不存在
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token))
            return ServerResponse.createByErrorMessage("token无效或过期");
        if (StringUtils.equals(forgetToken, token)) {
            String newPassowrd = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByName(username, newPassowrd);
            if (rowCount > 0){
                return ServerResponse.createBySuccessMessage("更新密码成功");
            }
        }else{
            return ServerResponse.createByErrorMessage("token无效或过期,请重新获取token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse<String> resetPassword(User user, String passwordOld, String passwordNew) {
        //防止横向越权，校验这个用户的旧密码是属于这个用户的
        int userCount=userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if (userCount<=0)
            return  ServerResponse.createByErrorMessage("旧密码错误");
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        //user.setUpdateTime(new Date());
        //sql中updateTime已经加内置函数now();
        int updateCount=userMapper.updateByPrimaryKeySelective(user);
        if (updateCount>0)
            return  ServerResponse.createBySuccessMessage("密码更新成功");
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        //username不能更新，email校验是否已经有了，有了也不能是当前用户的
        int emailCount=userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if (emailCount>0)
            return ServerResponse.createByErrorMessage("改邮箱地址已经被注册");
        User updateUser=new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        int updateCount=userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount>0)
            return ServerResponse.createBySuccessMessage("个人信息更新成功",updateUser);
        return ServerResponse.createByErrorMessage("个人信息更新失败");
    }

    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        User user=userMapper.selectByPrimaryKey(userId);
        if (user==null)
            return ServerResponse.createByErrorMessage("用户不存在");
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }


}
