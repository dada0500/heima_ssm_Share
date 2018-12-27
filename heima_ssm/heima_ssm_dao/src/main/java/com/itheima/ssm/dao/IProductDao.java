package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface IProductDao {

    //根据id查询产品信息
    @Select("select * from product where id = #{id}")
    Product findById(String id);

    //查询所有的产品信息
    @Select("select * from product")
    List<Product> findAll();

    //产品增加
    @Insert ("insert into PRODUCT (productnum, productname, cityname, departuretime, productprice,productdesc, productstatus)values ( #{productNum}, #{productName}, #{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save (Product product);
//    public List<Product> findAll() throws Exception;
}
