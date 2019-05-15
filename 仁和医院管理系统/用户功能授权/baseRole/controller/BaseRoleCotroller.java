package com.qhit.baseRole.controller;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRole.service.IBaseRoleService;
import com.qhit.baseRole.service.impl.BaseRoleServiceImpl;
import com.qhit.interfaces.Engineer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yy on 2018/12/2.
 */
@Controller
@RequestMapping("/baseRole")
public class BaseRoleCotroller {
   /* @Autowired
    Engineer engineer;
    @Autowired*/
    private IBaseRoleService baseRoleService = new BaseRoleServiceImpl();

    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        /*engineer.judge(123);*/
        List<BaseRole> list = baseRoleService.findAll();
        request.setAttribute("list",list);
        return "baseRole/list";
    }
    @RequestMapping("/distributeLoad")
    public String distributeLoad(BaseRole baseRole, Model model){
        List<BaseFunction> leftList = baseRoleService.distributeLeft(baseRole);
        List<BaseFunction> rightList = baseRoleService.distributeRight(baseRole);
        model.addAttribute("leftList",leftList);
        model.addAttribute("rightList",rightList);
        model.addAttribute("rid",baseRole.getRid());
        return "baseRole/distribute";
    }
    @RequestMapping("/distributeUpdate")
    public String distributeUpdate(BaseRole baseRole,String fids){
        baseRoleService.distributeUpdate(baseRole.getRid(),fids);
        return "forward:list.action";
    }

}
