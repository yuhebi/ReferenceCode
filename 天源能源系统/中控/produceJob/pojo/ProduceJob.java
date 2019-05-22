package com.qhit.produceJob.pojo;


/** 
* Created by GeneratorCode on 2019/04/10
*/ 

public class ProduceJob { 
    private Integer jobid;
    private Integer devid;
    private String starttime;
    private String completetime;
    private Double duration;
    private Double amount;
    private Integer reportid;
    private String shipname;
    private String startjobtime;
    private String devname;

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getShipname() {
        return shipname;
    }

    public void setShipname(String shipname) {
        this.shipname = shipname;
    }

    public String getStartjobtime() {
        return startjobtime;
    }

    public void setStartjobtime(String startjobtime) {
        this.startjobtime = startjobtime;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) { 
        this.jobid = jobid;
    } 

    public Integer getDevid() { 
        return devid;
    }

    public void setDevid(Integer devid) { 
        this.devid = devid;
    } 

    public String getStarttime() { 
        return starttime;
    }

    public void setStarttime(String starttime) { 
        this.starttime = starttime;
    }
    public String getCompletetime() { 
        return completetime;
    }

    public void setCompletetime(String completetime) { 
        this.completetime = completetime;
    }
    public Double getDuration() { 
        return duration;
    }

    public void setDuration(Double duration) { 
        this.duration = duration;
    } 

    public Double getAmount() { 
        return amount;
    }

    public void setAmount(Double amount) { 
        this.amount = amount;
    } 

    public Integer getReportid() { 
        return reportid;
    }

    public void setReportid(Integer reportid) { 
        this.reportid = reportid;
    } 


 }