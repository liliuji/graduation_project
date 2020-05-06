package com.domain;

public class Presence {
    private Integer presenceid;

    private String presencename;

    private String presencecontent;

    private Integer volunteerid;

    private Integer activeid;

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

    public Integer getActiveid() {
        return activeid;
    }

    public void setActiveid(Integer activeid) {
        this.activeid = activeid;
    }
}