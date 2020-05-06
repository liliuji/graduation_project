package com.domain;

public class Activity {
    private Integer activityid;

    private String activityname;

    private String activitydate;

    private String activitylocation;

    private String deadline;

    private String activitystartdate;

    private String activityenddate;

    private Integer demand;

    private Integer byuserid;

    private String activityimgpath;

    private String releasetime;

    private String activityrequirement;

    public String getByUserName() {
        return byUserName;
    }

    public void setByUserName(String byUserName) {
        this.byUserName = byUserName;
    }

    private String byUserName;

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname == null ? null : activityname.trim();
    }

    public String getActivitydate() {
        return activitydate;
    }

    public void setActivitydate(String activitydate) {
        this.activitydate = activitydate == null ? null : activitydate.trim();
    }

    public String getActivitylocation() {
        return activitylocation;
    }

    public void setActivitylocation(String activitylocation) {
        this.activitylocation = activitylocation == null ? null : activitylocation.trim();
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline == null ? null : deadline.trim();
    }

    public String getActivitystartdate() {
        return activitystartdate;
    }

    public void setActivitystartdate(String activitystartdate) {
        this.activitystartdate = activitystartdate == null ? null : activitystartdate.trim();
    }

    public String getActivityenddate() {
        return activityenddate;
    }

    public void setActivityenddate(String activityenddate) {
        this.activityenddate = activityenddate == null ? null : activityenddate.trim();
    }

    public Integer getDemand() {
        return demand;
    }

    public void setDemand(Integer demand) {
        this.demand = demand;
    }

    public Integer getByuserid() {
        return byuserid;
    }

    public void setByuserid(Integer byuserid) {
        this.byuserid = byuserid;
    }

    public String getActivityimgpath() {
        return activityimgpath;
    }

    public void setActivityimgpath(String activityimgpath) {
        this.activityimgpath = activityimgpath == null ? null : activityimgpath.trim();
    }

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime == null ? null : releasetime.trim();
    }

    public String getActivityrequirement() {
        return activityrequirement;
    }

    public void setActivityrequirement(String activityrequirement) {
        this.activityrequirement = activityrequirement == null ? null : activityrequirement.trim();
    }
}