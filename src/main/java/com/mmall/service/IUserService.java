package com.mmall.service;

import com.mmall.common.ServerResponse;
import org.springframework.stereotype.Service;
import com.mmall.pojo.User;
@Service
public interface IUserService {

    public ServerResponse<User> login(String username, String password);
    public ServerResponse<String> register(User user);
    public  ServerResponse<String> checkValid(String string,String type);
    public  ServerResponse<String> selectQuestion(String username);
    public  ServerResponse<String> CheckAnswer(String username,String question ,String answer);
}
