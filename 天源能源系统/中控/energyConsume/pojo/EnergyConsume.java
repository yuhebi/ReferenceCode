package com.qhit.energyConsume.pojo;


/** 
* Created by GeneratorCode on 2019/04/11
*/ 

public class EnergyConsume { 
    private Integer consumeid;
    private Integer devid;
    private Double electric;
    private Double water;
    private Double oil;
    private Integer reportid;
    private String names;
    private String months;
    private String amounts;
    private String typename;
    private String devname;

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public Integer getConsumeid() { 
        return consumeid;
    }

    public void setConsumeid(Integer consumeid) { 
        this.consumeid = consumeid;
    } 

    public Integer getDevid() { 
        return devid;
    }

    public void setDevid(Integer devid) { 
        this.devid = devid;
    } 

    public Double getElectric() { 
        return electric;
    }

    public void setElectric(Double electric) { 
        this.electric = electric;
    } 

    public Double getWater() { 
        return water;
    }

    public void setWater(Double water) { 
        this.water = water;
    } 

    public Double getOil() { 
        return oil;
    }

    public void setOil(Double oil) { 
        this.oil = oil;
    } 

    public Integer getReportid() { 
        return reportid;
    }

    public void setReportid(Integer reportid) { 
        this.reportid = reportid;
    } 


 }