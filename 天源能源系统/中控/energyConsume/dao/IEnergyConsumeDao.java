package com.qhit.energyConsume.dao;

import com.qhit.energyConsume.pojo.EnergyConsume;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/** 
* Created by GeneratorCode on 2019/04/11
*/

@Mapper  
public interface IEnergyConsumeDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<EnergyConsume> search(EnergyConsume energyConsume);

    List findByDevid(Object devid);

    List findByElectric(Object electric);

    List findByWater(Object water);

    List findByOil(Object oil);

    List findByReportid(Object reportid);

    List<EnergyConsume> flowAmount(Map<String, Object> map);

    List<EnergyConsume> electricConsume(Map<String, Object> map);

    List<EnergyConsume> devTypeConsume(Map<String, Object> map);

    List<EnergyConsume> flowConsume(Map<String, Object> map);

    List<EnergyConsume> devConsume(Map<String, Object> map);

    List<EnergyConsume> oilConsume(Map<String, Object> map);

    List<EnergyConsume> waterConsume(Map<String, Object> map);
}