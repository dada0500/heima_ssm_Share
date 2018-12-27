package com.itheima.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询所有订单
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") int page,@RequestParam(name="size",required = true,defaultValue = "3") int size){
        ModelAndView mv = new ModelAndView ();
        List<Orders> orders = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo (orders);
//        mv.addObject ("ordersList",orders);
        mv.addObject ("pageInfo",pageInfo);
        mv.setViewName ("orders-list");
        return mv;
    }

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findByid(String id){
        Orders order = ordersService.findById(id);
        ModelAndView mv = new ModelAndView ();
        mv.addObject ("orders",order);
        mv.setViewName ("orders-show");
        return mv;
    }
}
