package com.itheima.ssm.service;

import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserInfoService extends UserDetailsService {
    List<UserInfo> findAll (int page,int size);

    void save (UserInfo userInfo);

    UserInfo findById (String id);

    void del (String id);

    void addRoleToUser (String userId, String[] ids);

    void delRoleFromUser (String userId, String[] ids);
}
