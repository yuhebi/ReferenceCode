package com.qhit.baseUser.controller;

import com.alibaba.fastjson.JSON;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import com.qhit.utils.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by yy on 2018/11/26.
 */
@Controller
@RequestMapping("/baseUser")
public class BaseUserController {

    private IBaseUserService baseUserService = new BaseUserServiceImpl();
    private IBaseUserRoleService baseUserRoleService = new BaseUserRoleServiceImpl();

    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<BaseUser> list = baseUserService.findAll();
        request.setAttribute("list",list);
        return "baseUser/list";
    }
    @RequestMapping("/insert")
    public String insert(BaseUser baseUser){
        MD5 md5 = new MD5();
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        baseUserService.insert(baseUser);
        return "forward:list.action";
    }
    @RequestMapping("/del")
    public String delete(Integer userId){
        baseUserService.delete(userId);
        return "forward:list.action";
    }
    @RequestMapping("/load")
    public String load(BaseUser baseUsers, Model model){
        BaseUser baseUser = baseUserService.findById(baseUsers.getUserId());
        //回显
        model.addAttribute("baseUser",baseUser);
        return "baseUser/edit";
    }
    @RequestMapping("/update")
    public String update(BaseUser baseUser){
       baseUserService.updateSelective(baseUser);
        return "forward:list.action";
    }
    @RequestMapping("/lists")
    public String lists(String cname,String sex,BaseUser searchObject,HttpServletRequest request){
        List<BaseUser> list =baseUserService.findByfid(cname,sex);
        request.setAttribute("list",list);
        request.setAttribute("searchObject",searchObject);
        return "baseUser/list";
    }
    @RequestMapping("/login")
    public String login(BaseUser baseUser, HttpSession session,Model model){
        baseUser = baseUserService.login(baseUser);
        if(baseUser!=null){
            session.setAttribute("sessionUser",baseUser);
            return "index/home";
        }else {
            model.addAttribute("error","用户名或密码错误！");
            return "index/login";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("sessionUser");
        return "index/login";
    }
    @RequestMapping("/old")
    public void  oldPassword(BaseUser baseUser, HttpServletResponse response) throws IOException {
        boolean flag = baseUserService.findOld(baseUser);
        response.getWriter().write(flag?"Y":"N");
    }
    @RequestMapping("/updetpassword")
    public void  updetpassword(BaseUser baseUser, HttpServletResponse response) throws IOException {
       MD5 md5 = new MD5();
       baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        boolean flag = baseUserService.updateSelective(baseUser);
        response.getWriter().write(flag?"Y":"N");
    }
    @RequestMapping("/distributeLoad")
    public String distributeLoad(BaseUser baseUser,Model model){
        List<BaseRole> leftlist = baseUserService.findLeftRole(baseUser.getUserId());
        List<BaseRole> rightlist = baseUserService.findRightRole(baseUser.getUserId());
        model.addAttribute("leftlist",leftlist);
        model.addAttribute("rightlist",rightlist);
        model.addAttribute("userId",baseUser.getUserId());
        return "baseUser/distribute";
    }
   @RequestMapping("/distributeUpdate")
    public String distributeUpdate(Integer userId,String rids/*BaseUser baseUser,HttpServletRequest request*/){
        baseUserService.distributeUpdate(userId,rids);
       return "forward:list.action";
       /*String[] rids = request.getParameterValues("rid");
       IBaseUserRoleService baseUserRoleService = new BaseUserRoleServiceImpl();
       String sql = "delete from base_user_role where uid="+baseUser.getUserId();
       baseUserRoleService.freeUpdate(sql);
       for(String rid:rids){
           BaseUserRole baseUserRole = new BaseUserRole();
           baseUserRole.setRid(Integer.parseInt(rid));
           baseUserRole.setUid(baseUser.getUserId());
           baseUserRoleService.insert(baseUserRole);
       }
       return "forward:list.action";*/
   }
    @RequestMapping("/listAS")
    public void list(HttpServletResponse response) throws IOException {
        List<BaseUserRole> list = baseUserRoleService.findSAll();
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/$keshi")
    public void $keshi(HttpServletResponse response,Integer $keshi) throws IOException {
        BaseUser list = baseUserService.findKeShi($keshi);
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
}
