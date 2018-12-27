package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll (int page, int size) {
        PageHelper.startPage (page,size);
        return roleDao.findAll();
    }

    @Override
    public void add (Role role) {
        roleDao.add(role);
    }

    @Override
    public void delete (String roleId) {
        roleDao.delUsersByRoleId(roleId);
        roleDao.delPermissionByRoleId(roleId);
        roleDao.deleteById(roleId);
    }

    @Override
    public List<Role> findOtherByUserId (String id) {
        return roleDao.findOtherByUserId(id);
    }

    @Override
    public Role findById (String id) {
        return roleDao.findById(id);
    }

    @Override
    public void addPermissionToRole (String roleId, String[] ids) {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public List<Role> findRoleByUserId (String id) {
        return roleDao.findRoleByUserId(id);
    }
}
