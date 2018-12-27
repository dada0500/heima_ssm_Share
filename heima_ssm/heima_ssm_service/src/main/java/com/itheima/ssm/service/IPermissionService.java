package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll ();

    void save (Permission permission);

    void delete (String id);

    List<Permission> findOtherByRoleId (String id);
}
