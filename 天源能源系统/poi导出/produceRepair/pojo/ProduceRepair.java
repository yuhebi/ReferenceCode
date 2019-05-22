package com.qhit.produceRepair.pojo;


/** 
* Created by GeneratorCode on 2019/04/09
*/ 

public class ProduceRepair { 
    private Integer repairid;
    private Integer devid;
    private String cause;
    private Integer cost;
    private String repairdate;
    private String devname;


    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public Integer getRepairid() {
        return repairid;
    }

    public void setRepairid(Integer repairid) { 
        this.repairid = repairid;
    } 

    public Integer getDevid() { 
        return devid;
    }

    public void setDevid(Integer devid) { 
        this.devid = devid;
    } 

    public String getCause() { 
        return cause;
    }

    public void setCause(String cause) { 
        this.cause = cause;
    }
    public Integer getCost() { 
        return cost;
    }

    public void setCost(Integer cost) { 
        this.cost = cost;
    } 

    public String getRepairdate() { 
        return repairdate;
    }

    public void setRepairdate(String repairdate) { 
        this.repairdate = repairdate;
    }

 }