package com.qhit.baseFlow.pojo;


import com.qhit.baseCompany.pojo.BaseCompany;
import com.qhit.baseDevice.pojo.BaseDevice;

/**
* Created by GeneratorCode on 2019/04/09
*/ 

public class BaseFlow { 
    private Integer flowid;
    private String flowname;
    private Integer dljid;
    private Integer zcjid;
    private Integer pdjid;
    private Integer compid;
    private String dljname;
    private String zcjname;
    private String pdjname;
    private String compname;

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getDljname() {
        return dljname;
    }

    public void setDljname(String dljname) {
        this.dljname = dljname;
    }

    public String getZcjname() {
        return zcjname;
    }

    public void setZcjname(String zcjname) {
        this.zcjname = zcjname;
    }

    public String getPdjname() {
        return pdjname;
    }

    public void setPdjname(String pdjname) {
        this.pdjname = pdjname;
    }

    public Integer getFlowid() {
        return flowid;
    }

    public void setFlowid(Integer flowid) { 
        this.flowid = flowid;
    } 

    public String getFlowname() { 
        return flowname;
    }

    public void setFlowname(String flowname) { 
        this.flowname = flowname;
    }
    public Integer getDljid() { 
        return dljid;
    }

    public void setDljid(Integer dljid) { 
        this.dljid = dljid;
    } 

    public Integer getZcjid() { 
        return zcjid;
    }

    public void setZcjid(Integer zcjid) { 
        this.zcjid = zcjid;
    } 

    public Integer getPdjid() { 
        return pdjid;
    }

    public void setPdjid(Integer pdjid) { 
        this.pdjid = pdjid;
    } 

    public Integer getCompid() { 
        return compid;
    }

    public void setCompid(Integer compid) { 
        this.compid = compid;
    } 


 }