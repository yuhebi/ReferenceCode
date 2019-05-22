package com.qhit.produceJob.service.impl;

import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.produceJob.service.IProduceJobService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import com.qhit.produceJob.dao.IProduceJobDao;
import com.qhit.produceJob.pojo.ProduceJob;
import com.qhit.produceReport.pojo.ProduceReport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* Created by GeneratorCode on 2019/04/10
*/

@Service 
public class ProduceJobServiceImpl  implements IProduceJobService {

    @Resource 
    IProduceJobDao dao;

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
        ProduceJob produceJob = findById(id); 
        return dao.delete(produceJob); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public ProduceJob findById(Object id) { 
        List<ProduceJob> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<ProduceJob> search(ProduceJob produceJob) { 
        return dao.search(produceJob); 
    }

    @Override
    public List<ProduceJob> findSAll() {
        return dao.findSAll();
    }

    @Transactional
    @Override
    public boolean inserts(ProduceReport reportid, BaseFlow baseFlow) {
        SimpleDateFormat startjobtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat completetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        double duration;
        try {
           long time =  completetime.parse(reportid.getCompletetime()).getTime()-startjobtime.parse(reportid.getPlanjobtime()).getTime();
            duration = (double) time/(1000*60*60);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        ProduceJob produceJob1 = new ProduceJob();
        ProduceJob produceJob2 = new ProduceJob();
        ProduceJob produceJob3 = new ProduceJob();

        double f = Double.valueOf(String.valueOf(reportid.getCapacity()))/10;

        produceJob1.setDevid(baseFlow.getDljid());
        produceJob1.setStartjobtime(reportid.getStartjobtime());
        produceJob1.setCompletetime(reportid.getCompletetime());
        produceJob1.setDuration(Double.valueOf(df.format(duration)));
        produceJob1.setAmount(f*4);
        produceJob1.setReportid(reportid.getReportid());

        produceJob2.setDevid(baseFlow.getZcjid());
        produceJob2.setStartjobtime(reportid.getStartjobtime());
        produceJob2.setCompletetime(reportid.getCompletetime());
        produceJob2.setDuration(Double.valueOf(df.format(duration)));
        produceJob2.setAmount(f*4);
        produceJob2.setReportid(reportid.getReportid());

        produceJob3.setDevid(baseFlow.getPdjid());
        produceJob3.setStartjobtime(reportid.getStartjobtime());
        produceJob3.setCompletetime(reportid.getCompletetime());
        produceJob3.setDuration(Double.valueOf(df.format(duration)));
        produceJob3.setAmount(f*2);
        produceJob3.setReportid(reportid.getReportid());


        dao.insert(produceJob1);
        dao.insert(produceJob2);
        dao.insert(produceJob3);
        return true;
    }

}