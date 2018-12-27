
package com.itheima.ssm.service;

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleService {
    List<Role> findAll(int page, int size);

    void add (Role role);

    void delete (String roleId);

    List<Role> findOtherByUserId (String id);

    Role findById (String id);

    void addPermissionToRole (String roleId, String[] ids);

    List<Role> findRoleByUserId (String id);
}
