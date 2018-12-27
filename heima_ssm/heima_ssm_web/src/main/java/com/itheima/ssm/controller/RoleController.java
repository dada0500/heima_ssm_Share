package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IPermissionService;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") int page,@RequestParam(name = "size",defaultValue = "3") int size){
        ModelAndView mv = new ModelAndView ();
        List<Role> roleList = roleService.findAll (page,size);
        PageInfo pageInfo = new PageInfo (roleList);
        mv.addObject ("pageInfo",pageInfo);
        mv.setViewName ("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String add(Role role){
        roleService.add(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/del.do")
    public String del(String id){
        roleService.delete(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id) {
        ModelAndView mv = new ModelAndView ();
        Role role = roleService.findById(id);
        List<Permission> permissionList = permissionService.findOtherByRoleId(id);
        mv.addObject ("role", role);
        mv.addObject ("permissionList", permissionList);
        mv.setViewName ("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }
}
