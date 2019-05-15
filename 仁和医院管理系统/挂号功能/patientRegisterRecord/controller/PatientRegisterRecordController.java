package com.qhit.patientRegisterRecord.controller; 

import com.qhit.basePatientInfo.pojo.BasePatientInfo;
import com.qhit.basePatientInfo.service.IBasePatientInfoService;
import com.qhit.basePatientInfo.service.impl.BasePatientInfoServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import com.qhit.common.CommonUtil;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;
import com.qhit.patientRegisterRecord.service.IPatientRegisterRecordService; 
import com.qhit.patientRegisterRecord.service.impl.PatientRegisterRecordServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/22
*/
@Controller 
@RequestMapping("/patientRegisterRecord") 
public class PatientRegisterRecordController { 

    IPatientRegisterRecordService patientRegisterRecordService = new PatientRegisterRecordServiceImpl();
    IBasePatientInfoService basePatientInfoService = new BasePatientInfoServiceImpl();
    private IBaseUserRoleService baseUserRoleService = new BaseUserRoleServiceImpl();
    @RequestMapping("/insert")
    public String insert(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.insert(patientRegisterRecord); 
        return "forward:list.action"; 
    }
    @RequestMapping("/insertS")
    public String insertS(PatientRegisterRecord patientRegisterRecord, Integer yisheng, Integer keshi,Integer bingren, HttpSession session,Model model) {
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
        if (keshi!=null && !"".equals(keshi)) {
            patientRegisterRecord.setDeptId(keshi);//部门
        }
        if(yisheng!=null && !"".equals(yisheng)) {
            patientRegisterRecord.setDoctorId(yisheng);//医生
        }

        patientRegisterRecord.setRecordUser(sessionUser.getUserId());//挂号人
        patientRegisterRecord.setRegisterDate(CommonUtil.dateToStr(new Date()));//挂号时间
        patientRegisterRecord.setStatus(1);//状态
        patientRegisterRecord.setPatientId(bingren);//病人
        patientRegisterRecordService.insert(patientRegisterRecord);
        /*List<BaseUserRole> baseUserRole = baseUserRoleService.findSname(yisheng);
        model.addAttribute("baseUserRole",baseUserRole);*/
        if (keshi==null || "".equals(keshi)){
            return "forward:listYiSheng.action";
        }else if(yisheng==null || "".equals(yisheng)){
            return "forward:listKeShi.action";
        }
        else {
            return "forward:list.action";
        }
    }

    @RequestMapping("/listKeShi")
    public String listKeShi(Model model) throws IOException {
        List<PatientRegisterRecord> list = patientRegisterRecordService.findKAll();
        model.addAttribute("list",list);
        return "patientRegisterRecord/listKeShi";
    }
    @RequestMapping("/listYiSheng")
    public String listYiSheng(Model model) throws IOException {
        List<PatientRegisterRecord> list = patientRegisterRecordService.findYAll();
        model.addAttribute("list",list);
        return "patientRegisterRecord/listYiSheng";
    }
 
    @RequestMapping("/delete") 
    public String delete(Integer registerId, HttpServletResponse response) throws IOException { 
        patientRegisterRecordService.delete(registerId); 
        return "forward:list.action"; 
    }
    @RequestMapping("/delete1")
    public String delete1(Integer registerId, HttpServletResponse response) throws IOException {
        patientRegisterRecordService.delete(registerId);
        return "forward:listYiSheng.action";
    }
    @RequestMapping("/delete2")
    public String delete2(Integer registerId, HttpServletResponse response) throws IOException {
        patientRegisterRecordService.delete(registerId);
        return "forward:listKeShi.action";
    }
 
    @RequestMapping("/update") 
    public String update(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.update(patientRegisterRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.updateSelective(patientRegisterRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer registerId, Model model) { 
        PatientRegisterRecord patientRegisterRecord = patientRegisterRecordService.findById(registerId); 
        model.addAttribute("patientRegisterRecord",patientRegisterRecord); 
        return "patientRegisterRecord/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<PatientRegisterRecord> list = patientRegisterRecordService.findSAll();
        model.addAttribute("list",list); 
        return "patientRegisterRecord/list"; 
    }
    @RequestMapping("/listPRR")
    public String listPRR(Model model) throws IOException {
        List<BasePatientInfo> list = basePatientInfoService.findAll();
        model.addAttribute("list",list);
        return "patientRegisterRecord/PRRlist";
    }
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<PatientRegisterRecord> list = patientRegisterRecordService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    }
    @RequestMapping("/ajaxListS")
    public void ajaxLists(HttpServletResponse response,Integer bingren) throws IOException {
        PatientRegisterRecord list = patientRegisterRecordService.findBAll(bingren);
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }

    @RequestMapping("/search") 
    public String search(PatientRegisterRecord patientRegisterRecord,Model model) {
        List<PatientRegisterRecord> list = patientRegisterRecordService.search(patientRegisterRecord);
        model.addAttribute("list",list);
        model.addAttribute("searchObject",patientRegisterRecord);
        return "patientRegisterRecord/list"; 
    }

} 
