package com.itheima.ssm.dao;

import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IUserInfoDao {
    @Select("select * from users where username = #{username}")
    @Results(id = "usersResults",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findByUserId"))
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll ();

    @Insert("insert into users (email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save (UserInfo userInfo);

    @Select("select * from users where id = #{id}")
/*    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "email",column = "email"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findByUserId"))

    })*/
    @ResultMap ("usersResults")
    UserInfo findById (String id);

    @Delete ("delete from users_role where userid = #{id}")
    void delRoleByUserId (String id);

    @Delete ("delete from users where id = #{id}")
    void del (String id);

    @Insert ("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser (@Param("userId") String userId,@Param("roleId") String roleId);

    @Delete ("delete from users_role where userId = #{userId} and roleId = #{roleId}")
    void delRoleFromUser (@Param("userId") String userId,@Param("roleId") String roleId);
}
