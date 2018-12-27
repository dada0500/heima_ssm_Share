package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.dao.IUserInfoDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import com.itheima.ssm.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDao userInfoDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername (String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userInfoDao.findByUsername (s);
        } catch (Exception e) {
            e.printStackTrace ();
        }

        User user = new User(userInfo.getUsername (),userInfo.getPassword (),userInfo.getStatus () != 0,true,true,true  ,getAuthority(userInfo.getRoles ()));

        return user;
    }

    public Collection<? extends GrantedAuthority> getAuthority (List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<> ();
        for (Role role : roles) {
            list.add (new SimpleGrantedAuthority ("ROLE_" + role.getRoleName ()));
        }
        return list;
    }

    // 查询所有用户信息
    @Override
    public List<UserInfo> findAll (int page,int size) {
        PageHelper.startPage (page,size);
        return userInfoDao.findAll();
    }

    // 添加用户
    @Override
    public void save (UserInfo userInfo) {
        userInfo.setPassword (bCryptPasswordEncoder.encode (userInfo.getPassword ()));
        userInfoDao.save(userInfo);
    }

    // 根据用户id查询用户详细信息
    @Override
    public UserInfo findById (String id) {
        return userInfoDao.findById(id);
    }

    @Override
    public void del (String id) {
        userInfoDao.delRoleByUserId(id);
        userInfoDao.del(id);
    }

    @Override
    public void addRoleToUser (String userId, String[] ids) {
        for (String roleId : ids) {
            userInfoDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void delRoleFromUser (String userId, String[] ids) {
        for (String roleId : ids) {
            userInfoDao.delRoleFromUser(userId,roleId);
        }
    }

}
