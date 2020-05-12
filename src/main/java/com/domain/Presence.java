package com.domain;

public class Presence {
    private Integer presenceid;

    private String presencename;

    private String presencecontent;

    private Integer volunteerid;

    private Integer activityid;

    private String presenceimgpath;

    private String activename;

    private String volunteeraccount;

    private String volunteername;

    public Presence() {
    }

    public String getPresenceimgpath() {
        return presenceimgpath;
    }

    public void setPresenceimgpath(String presenceimgpath) {
        this.presenceimgpath = presenceimgpath;
    }

    public Integer getPresenceid() {
        return presenceid;
    }

    public void setPresenceid(Integer presenceid) {
        this.presenceid = presenceid;
    }

    public String getPresencename() {
        return presencename;
    }

    public void setPresencename(String presencename) {
        this.presencename = presencename == null ? null : presencename.trim();
    }

    public String getPresencecontent() {
        return presencecontent;
    }

    public void setPresencecontent(String presencecontent) {
        this.presencecontent = presencecontent == null ? null : presencecontent.trim();
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

    public String getActivename() {
        return activename;
    }

    public void setActivename(String activename) {
        this.activename = activename;
    }

    public String getVolunteername() {
        return volunteername;
    }

    public void setVolunteername(String volunteername) {
        this.volunteername = volunteername;
    }

    public String getVolunteeraccount() {
        return volunteeraccount;
    }

    public void setVolunteeraccount(String volunteeraccount) {
        this.volunteeraccount = volunteeraccount;
    }
}