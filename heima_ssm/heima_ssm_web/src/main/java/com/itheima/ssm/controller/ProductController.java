package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") int page,@RequestParam(name = "size",defaultValue = "3") int size){
        ModelAndView mv = new ModelAndView ();
        List<Product> list = productService.findAll (page,size);
        PageInfo pageInfo = new PageInfo (list);
        mv.addObject ("pageInfo",pageInfo);
//        mv.addObject ("productList",list);
        mv.setViewName ("product-list");
        return mv;
    }

    //产品增加
    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
}
