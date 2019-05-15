package com.qhit.patientRegisterRecord.service;

import java.util.List;

import com.qhit.doctorVisitRecord.pojo.DoctorVisitRecord;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;
/**
* Created by GeneratorCode on 2018/12/22
*/

public interface IPatientRegisterRecordService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    PatientRegisterRecord findById(Object id);

    boolean freeUpdate(String sql);

    List<PatientRegisterRecord> search(PatientRegisterRecord patientRegisterRecord);

    List<PatientRegisterRecord> findSAll();

    List<PatientRegisterRecord> findKAll();

    List<PatientRegisterRecord> findYAll();

    PatientRegisterRecord findBAll(Integer bingren);

    List<PatientRegisterRecord> findZAll(Integer userId);
}