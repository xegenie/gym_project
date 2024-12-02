package com.gym.gym.service;

import java.util.List;

import com.gym.gym.domain.UserAuth;
import com.gym.gym.domain.Users;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

     //로그인
    public boolean login(Users user, HttpServletRequest request) throws Exception;


       public List<Users> list() throws Exception;

       public Users selectId(String id) throws Exception;

    public Users select(Long no) throws Exception;
    
    public int join(Users user) throws Exception;

    public int update(Users user) throws Exception;

    public int insertAuth(UserAuth userAuth) throws Exception;
    
    public int updateAuth(UserAuth userAuth) throws Exception;

    public int delete(Users user) throws Exception;

    public int delteAuth(UserAuth userAuth) throws Exception;

    
}
