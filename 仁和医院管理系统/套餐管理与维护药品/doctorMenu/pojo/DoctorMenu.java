package com.qhit.doctorMenu.pojo;


import com.qhit.baseUser.pojo.BaseUser;
import com.sun.org.glassfish.gmbal.Description;


/**
* Created by GeneratorCode on 2018/12/19
*/

public class DoctorMenu {

    private Integer menuId;    //套餐ID
    private String menuName;    //套餐名称
    private Integer userId;    //用户
    private String description;    //套餐描述
    private Integer type;    //套餐类型

    @Description("bean")
    private BaseUser baseUser;

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
        this.baseUser = baseUser;
    }

    public Integer getMenuId() {
        return menuId;
    }
 
    public void setMenuId(Integer menuId) { 
        this.menuId = menuId;
    }
 
    public String getMenuName() { 
        return menuName;
    }
 
    public void setMenuName(String menuName) { 
        this.menuName = menuName;
    }
 
    public Integer getUserId() { 
        return userId;
    }
 
    public void setUserId(Integer userId) { 
        this.userId = userId;
    }
 
    public String getDescription() { 
        return description;
    }
 
    public void setDescription(String description) { 
        this.description = description;
    }
 
    public Integer getType() { 
        return type;
    }
 
    public void setType(Integer type) { 
        this.type = type;
    }
 

 }