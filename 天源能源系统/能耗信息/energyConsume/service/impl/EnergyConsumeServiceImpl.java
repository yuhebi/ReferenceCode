package com.qhit.energyConsume.service.impl;

import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.energyConsume.dao.IEnergyConsumeDao;
import com.qhit.energyConsume.pojo.EnergyConsume;
import com.qhit.energyConsume.service.IEnergyConsumeService;
import com.qhit.produceReport.pojo.ProduceReport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

/**
* Created by GeneratorCode on 2019/04/11
*/

@Service 
public class EnergyConsumeServiceImpl  implements IEnergyConsumeService {

    @Resource
    IEnergyConsumeDao dao;

    @Override
    public boolean insert(Object object) {
        return dao.insert(object);
    }

    @Override
    public boolean update(Object object) {
        return dao.update(object);
    }

    @Override
    public boolean updateSelective(Object object) {
        return dao.updateSelective(object);
    }

    @Override
    public boolean delete(Object id) {
        EnergyConsume energyConsume = findById(id);
        return dao.delete(energyConsume);
    }

    @Override
    public List findAll() {
        return dao.findAll();
    }

    @Override
    public EnergyConsume findById(Object id) {
        List<EnergyConsume> list = dao.findById(id);
        return list.get(0);
    }

    @Override
    public List<EnergyConsume> search(EnergyConsume energyConsume) {
        return dao.search(energyConsume);
    }


    @Transactional
    @Override
    public boolean inserts(ProduceReport reportid, BaseFlow baseFlow) {

        Random random = new Random();

        DecimalFormat df = new DecimalFormat("#.##");

        double f = Double.valueOf(String.valueOf(reportid.getCapacity())) / 10;
        EnergyConsume energyConsume1 = new EnergyConsume();
        EnergyConsume energyConsume2 = new EnergyConsume();
        EnergyConsume energyConsume3 = new EnergyConsume();

        energyConsume1.setReportid(baseFlow.getDljid());
        energyConsume1.setElectric(Double.valueOf(df.format((random.nextInt(201) + 100) * f * 4)));
        energyConsume1.setWater(Double.valueOf(df.format((random.nextInt(10) + 1) * f * 4)));
        energyConsume1.setOil(Double.valueOf(df.format((random.nextInt(31) + 10) * f * 4)));
        energyConsume1.setReportid(reportid.getReportid());
        energyConsume2.setReportid(baseFlow.getZcjid());
        energyConsume2.setElectric(Double.valueOf(df.format((random.nextInt(201) + 100) * f * 4)));
        energyConsume2.setWater(Double.valueOf(df.format((random.nextInt(10) + 1) * f * 4)));
        energyConsume2.setOil(Double.valueOf(df.format((random.nextInt(31) + 10) * f * 4)));
        energyConsume2.setReportid(reportid.getReportid());
        energyConsume3.setReportid(baseFlow.getPdjid());
        energyConsume3.setElectric(Double.valueOf(df.format((random.nextInt(201) + 100) * f * 2)));
        energyConsume3.setWater(Double.valueOf(df.format((random.nextInt(10) + 1) * f * 2)));
        energyConsume3.setOil(Double.valueOf(df.format((random.nextInt(31) + 10) * f * 2)));
        energyConsume3.setReportid(reportid.getReportid());

        dao.insert(energyConsume1);
        dao.insert(energyConsume2);
        dao.insert(energyConsume3);
        return true;
    }

    @Override
    public List<EnergyConsume> flowAmount(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("compid", baseUser.getCompid());
        return dao.flowAmount(map);
    }


    @Override
    public List<EnergyConsume> electricConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("compid", baseUser.getCompid());
        return dao.electricConsume(map);
    }

    @Override
    public List<EnergyConsume> devTypeConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("compid", baseUser.getCompid());
        return dao.devTypeConsume(map);
    }

    @Override
    public List<EnergyConsume> flowConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("compid", baseUser.getCompid());
        return dao.flowConsume(map);
    }

    @Override
    public List<EnergyConsume> devConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("compid", baseUser.getCompid());
        return dao.devConsume(map);
    }

    @Override
    public List<EnergyConsume> oilConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("compid", baseUser.getCompid());
        return dao.oilConsume(map);
    }

    @Override
    public List<EnergyConsume> waterConsume(String year, BaseUser baseUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("compid", baseUser.getCompid());
        return dao.waterConsume(map);
}

    @Override
    public Map<String, Object> listChangeMapLeftJoin(List<EnergyConsume> energyConsumes) {
//        创建columns数组并存入数据(即流程名称及"月份")
//        长度为jobAmounts.size()+1
        String[] columns = new String[energyConsumes.size() + 1];
        columns[0] = "月份";
        for (int i = 1; i < columns.length; i++) {
            columns[i] = energyConsumes.get(i - 1).getNames();
        }
//        创建rows<Map>集合,即对象为Map的rows集合
        List<Map> rows = new ArrayList<>();
//        创建一个数组存入月份,即一月~十二月,便于生成rowsObject中的"月份"属性
        String[] months = {"1月", "2月", "3月", "4月", "5月", "6月",
                "7月", "8月", "9月", "10月", "11月", "12月"};
//        设置月份属性并将月份属性存入rows集合
//        遍历months数组
        for (String month : months) {
//            创建map对象存入属性"月份"
            Map<String, String> map = new HashMap<>();
//            生成属性
            map.put("月份", month);
//         存入rows集合
            rows.add(map);
        }
//       遍历jobAmounts集合,向rows存入解析数据
        for (EnergyConsume ja : energyConsumes) {
//            判断columns长度,即除"月份"对象是否有其他数据
            if (columns.length != 1) {
                //            判断数据是否为空
                if (ja.getAmounts() == null) {
                    for (int i = 0; i < rows.size(); i++) {
//                向对应的rowsObject存入属性及值
                        rows.get(i).put(ja.getNames(), "0");
                    }
                } else {
//               分割数据
                    String[] arr = ja.getAmounts().split("[,]");
//              通过fori遍历rows,便于向rows表中对应的对象存入分割的数据
                    for (int i = 0; i < rows.size(); i++) {
//               向对应的rowsObject存入属性及值
                        rows.get(i).put(ja.getNames(), arr[i]);
                    }
                }
            }
        }
//         创建chartData Map集合
        Map<String, Object> chartData = new HashMap<>();
//        将columns数组及rows集合存入chartData
        chartData.put("columns", columns);
        chartData.put("rows", rows);
        return chartData;//chartData
    }
}