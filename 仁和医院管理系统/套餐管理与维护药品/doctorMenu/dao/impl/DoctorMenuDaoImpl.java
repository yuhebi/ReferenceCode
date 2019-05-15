package com.qhit.doctorMenu.dao.impl;

import com.qhit.doctorMenu.dao.IDoctorMenuDao;
import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/19
*/

public class DoctorMenuDaoImpl extends BaseDao implements IDoctorMenuDao {

    @Override
    public List findAll() { 
        String sql = "select * from doctor_menu"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findById(Object id) { 
        String sql = "select * from doctor_menu where menu_id='"+id+"'"; 
        return freeFind(sql); 
    }


    @Override 
    public List findByMenuName(Object menuName) { 
        String sql = "select * from doctor_menu where menu_name='"+menuName+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByUserId(Object userId) { 
        String sql = "select * from doctor_menu where user_id='"+userId+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByDescription(Object description) { 
        String sql = "select * from doctor_menu where description='"+description+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByType(Object type) { 
        String sql = "select * from doctor_menu where type='"+type+"'"; 
        return freeFind(sql); 
    } 




}