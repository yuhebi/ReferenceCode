package com.qhit.doctorMenu.service;

import java.util.List;
import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.medicineCode.pojo.MedicineCode;

/**
* Created by GeneratorCode on 2018/12/19
*/

public interface IDoctorMenuService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    DoctorMenu findById(Object id);

    boolean freeUpdate(String sql);

    List<DoctorMenu> search(DoctorMenu doctorMenu);

    List<DoctorMenu> findSAll();

    boolean findByName(String menuName);


    List<MedicineCode> findLeftMedicine(Integer menuId);

    List<MedicineCode> findRightMedicine(Integer menuId);

    List<DoctorMenu> findid(Integer $opp);
}