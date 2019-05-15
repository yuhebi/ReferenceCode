package com.qhit.baseUser.service.impl;

import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.service.IBaseUserService;
import java.util.List;
import com.qhit.baseUser.dao.IBaseUserDao;
import com.qhit.baseUser.dao.impl.BaseUserDaoImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import com.qhit.utils.MD5;

/**
* Created by GeneratorCode on 2018/11/26
*/

public class BaseUserServiceImpl  implements IBaseUserService {

    IBaseUserDao dao = new BaseUserDaoImpl();

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
        BaseUser baseUser = findById(id); 
        return dao.delete(baseUser); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseUser findById(Object id) { 
        List<BaseUser> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List<BaseUser> findByfid(String cname, String sex) {

        String sql = "SELECT * FROM base_user ";
        String str = "where 1=1";
        if(cname!=null && !"".equals(cname)){
            str+=" and cname LIKE '%"+cname+"%'";
        }
        if(sex!=null || !"".equals(sex)){
            str+=" and sex LIKE '%"+sex+"%'";
        }
        return dao.freeFind(sql + str);
    }

    @Override
    public BaseUser login(BaseUser baseUser) {
        MD5 md5 = new MD5();
        String password = md5.getMD5ofStr(baseUser.getPassword());
        String sql = "SELECT * from base_user bu LEFT JOIN base_user_role bur ON bu.user_id=bur.uid\n" +
                "\t\t\t   LEFT JOIN base_role br ON bur.rid=br.rid\n" +
                "\t\t\t   LEFT JOIN base_role_function brf ON br.rid = brf.rid\n" +
                "\t\t\t   LEFT JOIN base_function bf ON brf.fid=bf.fid";
        sql += " where bu.user_name ='"+baseUser.getUserName()+"' and bu.password='"+password+"'";
        List<BaseUser> list = dao.freeFind(sql);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean findOld(BaseUser baseUser) {
        MD5 md5 = new MD5();
        String password = md5.getMD5ofStr(baseUser.getPassword());
        String sql = "select * from base_user where user_id='"+baseUser.getUserId()+"' and password = '"+password+"'";
        List<BaseUser> list = dao.freeFind(sql);
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<BaseRole> findLeftRole(Integer userId) {
        String sql = "SELECT * from base_role br WHERE br.rid NOT IN\n" +
                "\t\t(SELECT rid \n" +
                "\t\t from base_user_role bur JOIN base_user bu ON bur.uid =bu.user_id \n" +
                "\t\t AND bu.user_id='"+userId+"')";
        return dao.freeFind(sql);
    }

    @Override
    public List<BaseRole> findRightRole(Integer userId) {
        String sql = "SELECT * from base_role br WHERE br.rid IN\n" +
                "\t\t(SELECT rid \n" +
                "\t\t from base_user_role bur JOIN base_user bu ON bur.uid =bu.user_id \n" +
                "\t\t AND bu.user_id='"+userId+"')";
        return dao.freeFind(sql);
    }

    @Override//不需要了
    public void distributeUpdate(Integer userId, String rids) {
        //拆分rid
        String[] arr = rids.substring(0, rids.length() - 1).split(",");
        String sql = "delete from base_user_role where uid="+userId;
        IBaseUserRoleService baseUserRoleService = new BaseUserRoleServiceImpl();
        //自由更新
        baseUserRoleService.freeUpdate(sql);
        for (String rid:arr){
            BaseUserRole baseUserRole = new BaseUserRole();
            baseUserRole.setRid(Integer.parseInt(rid));
            baseUserRole.setUid(userId);
            baseUserRoleService.insert(baseUserRole);
        }
    }

    @Override
    public BaseUser findKeShi(Integer $keshi) {
        String sql = "SELECT * from base_user bu JOIN base_dept bd ON bu.dept_id=bd.dept_id WHERE bu.dept_id='"+$keshi+"'";
        List<BaseUser> list = dao.freeFind(sql);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}