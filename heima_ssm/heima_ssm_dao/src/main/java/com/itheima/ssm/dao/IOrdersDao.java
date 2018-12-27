package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select ("select * from orders")
    @Results({
            @Result(id = true, property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),
//            @Result(property = "productId",column = "productId"),
//            @Result(property = "memberId",column = "memberId"),
            @Result(property = "product",column = "productId",
                   javaType = Product.class,one=@One(select = "com.itheima.ssm.dao.IProductDao.findById"))
    })
    List<Orders> findAll ();


    @Select ("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "product",column = "productId",
                    javaType = Product.class,one=@One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findById"))
    })
    Orders findById (String id);
}
