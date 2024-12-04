package com.gym.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.gym.domain.UserAuth;
import com.gym.gym.domain.Users;

@Mapper
public interface UserMapper {
    
    public List<Users> list() throws Exception;

    public Users selectId(String id) throws Exception;

    public Users select(Long no) throws Exception;
    
    public int join(Users user) throws Exception;

    public int update(Users user) throws Exception;

    public int insertAuth(UserAuth userAuth) throws Exception;
    
    public int updateAuth(UserAuth userAuth) throws Exception;

    public int delete(@RequestParam("no") Long no) throws Exception;

    public int deleteAuth(@RequestParam("no") Long no) throws Exception;

    public UserAuth selectAuth(Long no) throws Exception; 


}
