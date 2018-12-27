package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleid from users_role where userid = #{userid})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId);


    @Select("select * from role")
    List<Role> findAll ();

    @Insert ("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void add (Role role);

    @Delete ("delete from users_role where roleId = #{roleId}")
    void delUsersByRoleId (String roleId);

    @Delete ("delete from role_permission where roleId = #{roleId}")
    void delPermissionByRoleId (String roleId);

    @Delete ("delete from role where id = #{roleId}")
    void deleteById (String roleId);

    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findOtherByUserId (String id);

    @Select("select * from role where id = #{id}")
    Role findById (String id);

    @Insert ("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole (@Param("roleId") String roleId,@Param("permissionId") String permissionId);

    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    List<Role> findRoleByUserId (String id);
}
