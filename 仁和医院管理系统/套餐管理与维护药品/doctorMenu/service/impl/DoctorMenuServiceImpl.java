package com.qhit.doctorMenu.service.impl;

import com.qhit.doctorMenu.service.IDoctorMenuService;
import java.util.List;
import com.qhit.doctorMenu.dao.IDoctorMenuDao;
import com.qhit.doctorMenu.dao.impl.DoctorMenuDaoImpl;
import com.qhit.doctorMenu.pojo.DoctorMenu;
import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine;
import com.qhit.doctorMenuMedicine.service.IDoctorMenuMedicineService;
import com.qhit.doctorMenuMedicine.service.impl.DoctorMenuMedicineServiceImpl;
import com.qhit.medicineCode.pojo.MedicineCode;

/**
* Created by GeneratorCode on 2018/12/19
*/

public class DoctorMenuServiceImpl  implements IDoctorMenuService {

    IDoctorMenuDao dao = new DoctorMenuDaoImpl();

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
        DoctorMenu doctorMenu = findById(id); 
        return dao.delete(doctorMenu); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public DoctorMenu findById(Object id) { 
        List<DoctorMenu> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<DoctorMenu> search(DoctorMenu doctorMenu) {
            String sql = "select * from doctor_menu where 1=1 "; 
            if (doctorMenu.getMenuName()!=null && !"".equals(doctorMenu.getMenuName())){        
                sql+=" and menu_name like '%"+doctorMenu.getMenuName()+"%' ";        
            } 
            if (doctorMenu.getUserId()!=null && !"".equals(doctorMenu.getUserId())){        
                sql+=" and user_id like '%"+doctorMenu.getUserId()+"%' ";        
            } 
            if (doctorMenu.getDescription()!=null && !"".equals(doctorMenu.getDescription())){        
                sql+=" and description like '%"+doctorMenu.getDescription()+"%' ";        
            } 
            if (doctorMenu.getType()!=null && !"".equals(doctorMenu.getType())){        
                sql+=" and type like '%"+doctorMenu.getType()+"%' ";        
            } 
            List<DoctorMenu> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<DoctorMenu> findSAll() {
        String sql = "SELECT * from doctor_menu dm JOIN base_user bu ON dm.user_id=bu.user_id";
        return dao.freeFind(sql);
    }

    @Override
    public boolean findByName(String menuName) {
        String sql = "SELECT * from doctor_menu WHERE menu_name='"+menuName+"'";
        List<DoctorMenu> list = dao.freeFind(sql);
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }
    @Override
    public List<MedicineCode> findLeftMedicine(Integer menuId) {
        String sql = "SELECT * from medicine_code WHERE code_id NOT IN\n" +
                "\t\t(SELECT mc.code_id from doctor_menu dm JOIN doctor_menu_medicine dmm\n" +
                "\t\t\t\t\t\t\tON dm.menu_id=dmm.menu_id\n" +
                "\t\t\t\t\t\t\tJOIN medicine_code mc\n" +
                "\t\t\t\t\t\t\tON mc.code_id=dmm.code_id " +
                "AND dm.menu_id='"+menuId+"')";
        return dao.freeFind(sql);
    }

    @Override
    public List<MedicineCode> findRightMedicine(Integer menuId) {
        String sql = "SELECT * from medicine_code WHERE code_id IN\n" +
                "\t\t(SELECT mc.code_id from doctor_menu dm JOIN doctor_menu_medicine dmm\n" +
                "\t\t\t\t\t\t\tON dm.menu_id=dmm.menu_id\n" +
                "\t\t\t\t\t\t\tJOIN medicine_code mc\n" +
                "\t\t\t\t\t\t\tON mc.code_id=dmm.code_id " +
                "AND dm.menu_id='"+menuId+"')";
        return dao.freeFind(sql);
    }

    @Override
    public List<DoctorMenu> findid(Integer $opp) {
        String sql = "SELECT * from doctor_menu WHERE menu_id = '"+$opp+"'";
        return dao.freeFind(sql);
    }


}