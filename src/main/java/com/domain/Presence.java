package com.domain;

public class Presence {
    private Integer presenceid;

    private String presencename;

    private String presencecontent;

    private String volunteername;

    private String activename;

    private String presenceimgpath;

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

    public String getVolunteername() {
        return volunteername;
    }

    public void setVolunteername(String volunteername) {
        this.volunteername = volunteername;
    }

    public String getActivename() {
        return activename;
    }

    public void setActivename(String activename) {
        this.activename = activename;
    }
}