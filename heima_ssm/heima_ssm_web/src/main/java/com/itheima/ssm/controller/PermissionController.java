package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") int page,@RequestParam(name = "size",required = true,defaultValue = "3") int size){
        ModelAndView mv = new ModelAndView ();
        List<Permission> permissionList = permissionService.findAll();
        PageInfo pageInfo = new PageInfo (permissionList);
        mv.addObject ("pageInfo",pageInfo);
        mv.setViewName ("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(@RequestParam(name = "permission",required = true) Permission permission){
        ModelAndView mv = new ModelAndView ();
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/del.do")
    public String del(@RequestParam(name = "id",required = true) String id){
        permissionService.delete(id);
        return "redirect:findAll.do";
    }

}
