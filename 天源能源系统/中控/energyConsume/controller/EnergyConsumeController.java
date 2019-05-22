package com.qhit.energyConsume.controller;

import com.qhit.baseCompany.pojo.BaseCompany;
import com.qhit.baseCompany.service.IBaseCompanyService;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.energyConsume.pojo.EnergyConsume;
import com.qhit.energyConsume.service.IEnergyConsumeService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* Created by GeneratorCode on 2019/04/11
*/ 

@RestController 
@RequestMapping("/energyConsume")
public class EnergyConsumeController { 

    @Resource
    IEnergyConsumeService energyConsumeService;
    @Resource
    IBaseCompanyService baseCompanyService;
    @Resource
    IBaseFlowService baseFlowService;
    @RequestMapping("/insert") 
    public void insert(EnergyConsume energyConsume) {
        energyConsumeService.insert(energyConsume); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer consumeid) { 
        energyConsumeService.delete(consumeid); 
    } 

    @RequestMapping("/update") 
    public void update(EnergyConsume energyConsume) {
        energyConsumeService.update(energyConsume); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(EnergyConsume energyConsume) {
        energyConsumeService.updateSelective(energyConsume); 
    } 

    @RequestMapping("/load") 
    public EnergyConsume load(Integer consumeid) {
        EnergyConsume energyConsume = energyConsumeService.findById(consumeid);
        return energyConsume; 
    } 

    @RequestMapping("/list") 
    public List<EnergyConsume> list()  {
        List<EnergyConsume> list = energyConsumeService.findAll();
        return list; 
    } 

    @RequestMapping("/search") 
    public List<EnergyConsume> search(EnergyConsume energyConsume) {
        List<EnergyConsume> list = energyConsumeService.search(energyConsume);
        return list; 
    }



    @RequestMapping("/flowConsume")
    public Map<String, Object> flowConsume(String year, HttpSession session) {
        List<EnergyConsume> energyConsumes = energyConsumeService.flowConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }


    @RequestMapping("/devTypeConsume")
    public Map<String, Object> devTypeConsume(String year, HttpSession session) {
        List<EnergyConsume> energyConsumes = energyConsumeService.devTypeConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }


    @RequestMapping("/devConsume")
    public Map<String, Object> devConsume(String year, HttpSession session) {
        List<EnergyConsume> energyConsumes = energyConsumeService.devConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }



    @RequestMapping("/electricConsume")
    public Map<String, Object> electricConsume(String year, HttpSession session) {
        List<EnergyConsume> energyConsumes = energyConsumeService.electricConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }


    @RequestMapping("/waterConsume")
    public Map<String, Object> waterConsume(String year, HttpSession session){
        List<EnergyConsume> energyConsumes = energyConsumeService.waterConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
    }



    @RequestMapping("/oilConsume")
    public Map<String, Object> oilConsume(String year, HttpSession session) {
        List<EnergyConsume> energyConsumes = energyConsumeService.oilConsume(year,(BaseUser) session.getAttribute("baseUser"));
        Map<String, Object> chartData = energyConsumeService.listChangeMapLeftJoin(energyConsumes);
        return chartData;
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
        //定义一个企业总能耗consume
        double consume = 0;
        //定义一个集合存入map对象
        List<Map> m = new ArrayList<Map>();
        //通过for村循环，遍历该企业下的线程
        for (int i = 0; i < baseFlows.size(); i++) {
            //创建一个map1存入对象数据
            Map map1 = new HashMap();
            //定义一个线程总能耗consumes
            double consumes = 0;
            //查询某一年当前线程下的设备名和其工作量存入集合中
           List<Map> list = baseFlowService.selectmodel(year,baseFlows.get(i));
            //通过for村循环，遍历该线程下的设备
            for (int j = 0; j <list.size() ; j++) {
                //获取该设备的能耗
                consumes += (double)list.get(j).get("consume");
                //把该能耗存入map中（每循环一次存入一个）
                list.get(j).put("consume",list.get(j).get("consume")+"吨");
            }
            //该企业总能耗（每循环一个线程相加一次）
            consume+=consumes;
            //存入需要的信息
            map1.put("flow",baseFlows.get(i).getFlowname());//线程名
            map1.put("consume",consumes+"吨");//该线程总能耗
            map1.put("children",list);//存入该线程下的设备信息
            m.add(map1);

        }
        //整个企业
        map.put("comp",baseCompany.getCompname());//企业名
        map.put("consume",consume+"吨");//企业的总能耗
        map.put("children",m);//存入该企业下map信息
        return map;
    }

} 
