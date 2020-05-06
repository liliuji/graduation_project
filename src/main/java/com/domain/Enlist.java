package com.domain;

public class Enlist {
    private Integer enlistid;

    private String enlistreason;

    private String enlistdate;

    private Integer volunteerid;

    private Integer activityid;

    private String enliststatus;

    public String getEnliststatus() {
        return enliststatus;
    }

    public void setEnliststatus(String enliststatus) {
        this.enliststatus = enliststatus;
    }

    public Integer getEnlistid() {
        return enlistid;
    }

    public void setEnlistid(Integer enlistid) {
        this.enlistid = enlistid;
    }

    public String getEnlistreason() {
        return enlistreason;
    }

    public void setEnlistreason(String enlistreason) {
        this.enlistreason = enlistreason == null ? null : enlistreason.trim();
    }

    public String getEnlistdate() {
        return enlistdate;
    }

    public void setEnlistdate(String enlistdate) {
        this.enlistdate = enlistdate == null ? null : enlistdate.trim();
    }

    public Integer getVolunteerid() {
        return volunteerid;
    }

    public void setVolunteerid(Integer volunteerid) {
        this.volunteerid = volunteerid;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }
}