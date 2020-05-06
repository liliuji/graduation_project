package com.domain;

public class Notice {
    private Integer noticeid;

    private String noticetitle;

    private String noticecontent;

    private String noticecreatedate;

    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    public String getNoticetitle() {
        return noticetitle;
    }

    public void setNoticetitle(String noticetitle) {
        this.noticetitle = noticetitle == null ? null : noticetitle.trim();
    }

    public String getNoticecontent() {
        return noticecontent;
    }

    public void setNoticecontent(String noticecontent) {
        this.noticecontent = noticecontent == null ? null : noticecontent.trim();
    }

    public String getNoticecreatedate() {
        return noticecreatedate;
    }

    public void setNoticecreatedate(String noticecreatedate) {
        this.noticecreatedate = noticecreatedate == null ? null : noticecreatedate.trim();
    }
}