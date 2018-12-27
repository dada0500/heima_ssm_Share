package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import com.itheima.ssm.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IRoleService roleService;

    @RolesAllowed ("ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name = "size",required = true,defaultValue = "3") int size){
        ModelAndView mv = new ModelAndView ();
        List<UserInfo> userList = userInfoService.findAll(page,size);
        PageInfo pageInfo = new PageInfo (userList);
        mv.addObject ("pageInfo",pageInfo);
        mv.setViewName ("user-list");
        return mv;
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView ();
        UserInfo userInfo = userInfoService.findById(id);
        mv.addObject ("user",userInfo);
        mv.setViewName ("user-show");
        return mv;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/save.do")
    public String save(@RequestParam(required = true) UserInfo userInfo) {
        userInfoService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Secured ("ROLE_ADMIN")
    @RequestMapping("/del.do")
    public String del(@RequestParam(name = "id") String id) {
        userInfoService.del(id);
        return "redirect:findAll.do";
    }

    /**
     * 用户角色权限详情--添加所属角色
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndOtherRole.do")
    public ModelAndView findUserByIdAndOtherRole(String id){
        ModelAndView mv = new ModelAndView ();
        UserInfo userInfo = userInfoService.findById (id);
        List<Role> otherRoles = roleService.findOtherByUserId(id);
        mv.addObject ("user",userInfo);
        mv.addObject ("roleList",otherRoles);
        mv.setViewName ("user-role-add");
        return mv;
    }

    /**
     * 用户角色权限详情--删除所属角色
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv = new ModelAndView ();
        UserInfo userInfo = userInfoService.findById (id);
        List<Role> otherRoles = roleService.findRoleByUserId(id);
        mv.addObject ("user",userInfo);
        mv.addObject ("roleList",otherRoles);
        mv.setViewName ("user-role-del");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId, String[] ids) throws Exception {
        userInfoService.addRoleToUser(userId,ids);
        return "redirect:findById.do?id=" + userId;
    }

    @RequestMapping("/delRoleFromUser.do")
    public String delRoleFromUser(String userId, String[] ids) throws Exception {
        userInfoService.delRoleFromUser(userId,ids);
        return "redirect:findById.do?id=" + userId;
    }
}
