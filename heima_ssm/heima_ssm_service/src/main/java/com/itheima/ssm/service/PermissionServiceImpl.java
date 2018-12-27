package com.itheima.ssm.service;

import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll () {
        return permissionDao.findAll();
    }

    @Override
    public void save (Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public void delete (String id) {
        permissionDao.delRoleByPermissionId(id);
        permissionDao.deleteById(id);
    }

    @Override
    public List<Permission> findOtherByRoleId (String roleId) {
        return permissionDao.findOtherByRoleId(roleId);
    }
}
