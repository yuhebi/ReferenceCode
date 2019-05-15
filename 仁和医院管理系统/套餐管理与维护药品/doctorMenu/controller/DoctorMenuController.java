package com.qhit.doctorMenu.controller; 

import com.alibaba.fastjson.JSONObject;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.doctorMenu.service.IDoctorMenuService; 
import com.qhit.doctorMenu.service.impl.DoctorMenuServiceImpl;
import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine;
import com.qhit.doctorMenuMedicine.service.IDoctorMenuMedicineService;
import com.qhit.doctorMenuMedicine.service.impl.DoctorMenuMedicineServiceImpl;
import com.qhit.medicineCode.pojo.MedicineCode;
import com.qhit.medicineCode.service.IMedicineCodeService;
import com.qhit.medicineCode.service.impl.MedicineCodeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException; 
import java.util.List; 

/**
* Created by GeneratorCode on 2018/12/19
*/
@Controller 
@RequestMapping("/doctorMenu")
public class DoctorMenuController { 

    IDoctorMenuService doctorMenuService = new DoctorMenuServiceImpl();
    IDoctorMenuMedicineService doctorMenuMedicineService = new DoctorMenuMedicineServiceImpl();

    @RequestMapping("/insert") 
    public String insert(DoctorMenu doctorMenu, HttpSession session) {
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
        doctorMenu.setUserId(sessionUser.getUserId());
        doctorMenuService.insert(doctorMenu); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer menuId, HttpServletResponse response) throws IOException { 
        doctorMenuService.delete(menuId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(DoctorMenu doctorMenu) { 
        doctorMenuService.update(doctorMenu); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(DoctorMenu doctorMenu) { 
        doctorMenuService.updateSelective(doctorMenu); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer menuId, Model model) { 
        DoctorMenu doctorMenu = doctorMenuService.findById(menuId); 
        model.addAttribute("doctorMenu",doctorMenu); 
        return "doctorMenu/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<DoctorMenu> list = doctorMenuService.findSAll();
        model.addAttribute("list",list); 
        return "doctorMenu/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<DoctorMenu> list = doctorMenuService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    }
    @RequestMapping("/ajaxSList")
    public void ajaxSList(HttpServletResponse response,String menuName) throws IOException {
        boolean doctorMenu = doctorMenuService.findByName(menuName);
        //        响应
        JSONObject json = new JSONObject();
        json.put("flag",doctorMenu);
        response.getWriter().write(json.toJSONString());
//        String s = JSON.toJSONString(doctorMenu);
//        response.getWriter().write(s);
    }
    //套餐id
    @RequestMapping("/ajaxListid")
    public void ajaxListid(HttpServletResponse response,Integer $opp) throws IOException {
        List<DoctorMenu> list = doctorMenuService.findid($opp);
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/search") 
    public String search(DoctorMenu doctorMenu,Model model) { 
        List<DoctorMenu> list = doctorMenuService.search(doctorMenu); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",doctorMenu); 
        return "doctorMenu/list"; 
    }

    @RequestMapping("/distributeLoad")
    public String distributeLoad(DoctorMenu doctorMenu, Model model){
        List<MedicineCode> leftlist = doctorMenuService.findLeftMedicine(doctorMenu.getMenuId());
        List<MedicineCode> rightlist = doctorMenuService.findRightMedicine(doctorMenu.getMenuId());
        model.addAttribute("leftlist",leftlist);
        model.addAttribute("rightlist",rightlist);
        model.addAttribute("MenuId",doctorMenu.getMenuId());
        return "doctorMenu/distribute";
    }
    @RequestMapping("/distributeUpdate")
    public String distributeUpdate(DoctorMenuMedicine doctorMenuMedicine,String fid){
        doctorMenuMedicineService.distributeUpdate(doctorMenuMedicine.getMenuId(),fid);
        return "forward:list.action";
    }
 
} 
