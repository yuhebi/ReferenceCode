package com.qhit.produceRepair.controller; 

import com.qhit.produceRepair.pojo.ProduceRepair; 
import com.qhit.produceRepair.service.IProduceRepairService;
import com.qhit.utils.CommonUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/** 
* Created by GeneratorCode on 2019/04/09
*/ 

@RestController 
@RequestMapping("/produceRepair")
public class ProduceRepairController { 

    @Resource
    IProduceRepairService produceRepairService;
    @RequestMapping("/exportToExcel")
    public void exportToExcel()throws Exception{
        String title = "维修信息表";
        String[] name = {"序号","设备名称 ","维修信息","维修费用","维修时间"};
        //获取数据
        Connection connection = CommonUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT pr.repairid,bd.devname,pr.cause,pr.cost,pr.repairdate\n" +
                "FROM produce_repair pr JOIN base_device bd ON pr.devid=bd.devid";
        ResultSet rs = statement.executeQuery(sql);
        CommonUtil.exportExcel(title,name,rs);
        System.out.println("打印成功");

    }

    @RequestMapping("/insert") 
    public void insert(ProduceRepair produceRepair) { 
        produceRepairService.insert(produceRepair); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer repairid) { 
        produceRepairService.delete(repairid); 
    } 

    @RequestMapping("/update") 
    public void update(ProduceRepair produceRepair) { 
        produceRepairService.update(produceRepair); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(ProduceRepair produceRepair) { 
        produceRepairService.updateSelective(produceRepair); 
    } 

    @RequestMapping("/load") 
    public ProduceRepair load(Integer repairid) { 
        ProduceRepair produceRepair = produceRepairService.findById(repairid); 
        return produceRepair; 
    } 

    @RequestMapping("/list") 
    public List<ProduceRepair> list() throws IOException {

        List<ProduceRepair> list = produceRepairService.findSAll();
        return list; 
    }

    @RequestMapping("/search") 
    public List<ProduceRepair> search(ProduceRepair produceRepair) { 
        List<ProduceRepair> list = produceRepairService.search(produceRepair); 
        return list; 
    }

}
