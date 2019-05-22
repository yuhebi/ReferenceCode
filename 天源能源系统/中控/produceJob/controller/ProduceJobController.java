package com.qhit.produceJob.controller; 

import com.qhit.baseCompany.pojo.BaseCompany;
import com.qhit.baseCompany.service.IBaseCompanyService;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.produceJob.pojo.ProduceJob;
import com.qhit.produceJob.service.IProduceJobService;
import com.qhit.produceReport.service.IProduceReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController; 

/** 
* Created by GeneratorCode on 2019/04/10
*/ 

@RestController 
@RequestMapping("/produceJob") 
public class ProduceJobController { 

    @Resource 
    IProduceJobService produceJobService;
    @Resource
    IBaseFlowService baseFlowService;
    @Resource
    IProduceReportService produceReportService;
    @Resource
    IBaseCompanyService baseCompanyService;

    @RequestMapping("/insert") 
    public void insert(ProduceJob produceJob) {
        produceJobService.insert(produceJob);
    } 

    @RequestMapping("/delete") 
    public void delete(Integer jobid) { 
        produceJobService.delete(jobid); 
    } 

    @RequestMapping("/update") 
    public void update(ProduceJob produceJob) { 
        produceJobService.update(produceJob); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(ProduceJob produceJob) { 
        produceJobService.updateSelective(produceJob); 
    } 

    @RequestMapping("/load") 
    public ProduceJob load(Integer jobid) { 
        ProduceJob produceJob = produceJobService.findById(jobid); 
        return produceJob; 
    } 

    @RequestMapping("/list") 
    public List<ProduceJob> list()  { 
        List<ProduceJob> list = produceJobService.findSAll();
        return list; 
    } 

    @RequestMapping("/search") 
    public List<ProduceJob> search(ProduceJob produceJob) { 
        List<ProduceJob> list = produceJobService.search(produceJob); 
        return list; 
    }
    @RequestMapping("/model")
    public Object model(String year, HttpSession session){
        //先创建一个map存入对象数据
        Map map = new HashMap();
        //获取到的登录时存入session里的用户对象
        BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
        //获取用户对象里的企业id
        Integer compid = baseUser.getCompid();
        //通过企业ID在企业表查到企业信息
        BaseCompany baseCompany = baseCompanyService.findById(compid);
        //通过企业ID在流程表中查询到属于该企业的流程信息
        List<BaseFlow> baseFlows = baseFlowService.findByCompid(compid);
        //定义一个企业总工作量amounts
        double amounts = 0;
        //定义一个list集合存入map对象
        List<Map> m = new ArrayList<Map>();
        //通过for循环，遍历该企业下的线程
        for (int i = 0; i < baseFlows.size(); i++) {
            //创建一个map1存入对象数据
            Map map1 = new HashMap();
            //定义一个线程总工作量amount
            double amount = 0;
            //查询某一年当前线程下的设备名和其工作量存入集合中
            List<Map> list = produceReportService.selectModel(year,baseFlows.get(i));
            //通过for循环，遍历该线程下的设备
            for (int j = 0; j < list.size(); j++) {
                //获取该设备的工作量
                amount += (double)list.get(j).get("amount");
                //把该工作量存入map中（每循环一次存入一个）
                list.get(j).put("amount",list.get(j).get("amount")+"吨");
            }
            //企业的总工作量（每循环一个线程相加一次）
            amounts+=amount;
            //存入需要的信息
            map1.put("flow",baseFlows.get(i).getFlowname());//线程名
            map1.put("amount",amount+"吨");//该线程总工作量
            map1.put("children",list);//存入该线程下的设备信息
            m.add(map1);
        }
        System.out.println(m);
        //整个企业
        map.put("comp",baseCompany.getCompname());//企业名
        map.put("amount",amounts+"吨");//企业的总工作量
        map.put("children",m);//存入该企业下map信息
        return  map;
    }
} 
