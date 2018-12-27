package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    @Select("select * from traveller where id in(select travellerid from ORDER_TRAVELLER where orderid = #{id})")
    public List<Traveller> findById(String ordersid);
}
