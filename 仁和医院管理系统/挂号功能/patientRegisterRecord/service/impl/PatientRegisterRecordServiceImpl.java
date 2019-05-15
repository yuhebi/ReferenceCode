package com.qhit.patientRegisterRecord.service.impl;

import com.qhit.doctorVisitRecord.pojo.DoctorVisitRecord;
import com.qhit.patientRegisterRecord.service.IPatientRegisterRecordService;
import java.util.List;
import com.qhit.patientRegisterRecord.dao.IPatientRegisterRecordDao;
import com.qhit.patientRegisterRecord.dao.impl.PatientRegisterRecordDaoImpl;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;

/**
* Created by GeneratorCode on 2018/12/22
*/

public class PatientRegisterRecordServiceImpl  implements IPatientRegisterRecordService {

    IPatientRegisterRecordDao dao = new PatientRegisterRecordDaoImpl();

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
        PatientRegisterRecord patientRegisterRecord = findById(id); 
        return dao.delete(patientRegisterRecord); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public PatientRegisterRecord findById(Object id) { 
        List<PatientRegisterRecord> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<PatientRegisterRecord> search(PatientRegisterRecord patientRegisterRecord) {
            String sql = "select * from patient_register_record where 1=1 "; 
            if (patientRegisterRecord.getPatientId()!=null && !"".equals(patientRegisterRecord.getPatientId())){        
                sql+=" and patient_id like '%"+patientRegisterRecord.getPatientId()+"%' ";        
            } 
            if (patientRegisterRecord.getDeptId()!=null && !"".equals(patientRegisterRecord.getDeptId())){        
                sql+=" and dept_id like '%"+patientRegisterRecord.getDeptId()+"%' ";        
            } 
            if (patientRegisterRecord.getRegisterDate()!=null && !"".equals(patientRegisterRecord.getRegisterDate())){        
                sql+=" and register_date like '%"+patientRegisterRecord.getRegisterDate()+"%' ";        
            } 
            if (patientRegisterRecord.getRecordUser()!=null && !"".equals(patientRegisterRecord.getRecordUser())){        
                sql+=" and record_user like '%"+patientRegisterRecord.getRecordUser()+"%' ";        
            } 
            if (patientRegisterRecord.getDoctorId()!=null && !"".equals(patientRegisterRecord.getDoctorId())){        
                sql+=" and doctor_id like '%"+patientRegisterRecord.getDoctorId()+"%' ";        
            } 
            if (patientRegisterRecord.getStatus()!=null && !"".equals(patientRegisterRecord.getStatus())){        
                sql+=" and status like '%"+patientRegisterRecord.getStatus()+"%' ";        
            } 
            List<PatientRegisterRecord> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<PatientRegisterRecord>  findSAll() {
        String sql = "SELECT prr.*,cast(bus.cname as char) as cname,bpi.*,bd.*,bu.* from patient_register_record prr \n" +
                "\t\t\t\tJOIN base_patient_info bpi\n" +
                "\t\t\t\tON prr.patient_id=bpi.patient_id\n" +
                "\t\t\t\tJOIN base_dept bd\n" +
                "\t\t\t\tON bd.dept_id=prr.dept_id\n" +
                "\t\t\t\tJOIN base_user bu\n" +
                "\t\t\t\tON prr.record_user=bu.user_id join base_user bus on prr.doctor_id=bus.user_id";
        return dao.freeFind(sql);
    }

    @Override
    public List<PatientRegisterRecord> findKAll() {
        String sql = "SELECT * from patient_register_record prr \n" +
                "\t\t\t\tJOIN base_patient_info bpi\n" +
                "\t\t\t\tON prr.patient_id=bpi.patient_id\n" +
                "\t\t\t\tJOIN base_dept bd\n" +
                "\t\t\t\tON bd.dept_id=prr.dept_id\n" +
                "\t\t\t\tJOIN base_user bu\n" +
                "\t\t\t\tON prr.record_user=bu.user_id WHERE prr.doctor_id IS NULL ";
        return dao.freeFind(sql);
    }

    @Override
    public List<PatientRegisterRecord> findYAll() {
        String sql = "SELECT prr.*,cast(bus.cname as char) as cname,bpi.*,bu.* from patient_register_record prr \n" +
                "\t\t\t\tJOIN base_patient_info bpi\n" +
                "\t\t\t\tON prr.patient_id=bpi.patient_id\n" +
                "\t\t\t\tJOIN base_user bu\n" +
                "\t\t\t\tON prr.record_user=bu.user_id join base_user bus on prr.doctor_id=bus.user_id WHERE prr.dept_id IS NULL";
        return dao.freeFind(sql);
    }

    @Override
    public PatientRegisterRecord findBAll(Integer bingren) {
        String sql = "SELECT * from patient_register_record WHERE patient_id='"+bingren+"'";
        List<PatientRegisterRecord> list = dao.freeFind(sql);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<PatientRegisterRecord> findZAll(Integer userId) {
        String sql ="SELECT * from patient_register_record prr JOIN base_patient_info bpi\n" +
                "\t\t\t\t\t  ON prr.patient_id=bpi.patient_id\n" +
                "\t\t\t\t\t  WHERE prr.status='1' AND prr.doctor_id='"+userId+"'";
        return dao.freeFind(sql);
    }

   /* @Override
    public List<PatientRegisterRecord> searchs(String patientName) {
            String sql = "SELECT prr.*,cast(bus.cname as char) as cname,bpi.*,bd.*,bu.* from patient_register_record prr \n" +
                    "\t\t\t\tJOIN base_patient_info bpi\n" +
                    "\t\t\t\tON prr.patient_id=bpi.patient_id\n" +
                    "\t\t\t\tJOIN base_dept bd\n" +
                    "\t\t\t\tON bd.dept_id=prr.dept_id\n" +
                    "\t\t\t\tJOIN base_user bu\n" +
                    "\t\t\t\tON prr.record_user=bu.user_id join base_user bus on prr.doctor_id=bus.user_id where bpi.patient_name='"+patientName+"'";
            return dao.freeFind(sql);
    }*/


}