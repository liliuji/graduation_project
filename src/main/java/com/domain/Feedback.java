package com.domain;

public class Feedback {
    private Integer feedbackid;

    private String feedbackcontent;

    private String feedbackdate;

    private String feedbackreply;

    private String replydate;

    public Integer getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getFeedbackcontent() {
        return feedbackcontent;
    }

    public void setFeedbackcontent(String feedbackcontent) {
        this.feedbackcontent = feedbackcontent == null ? null : feedbackcontent.trim();
    }

    public String getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(String feedbackdate) {
        this.feedbackdate = feedbackdate == null ? null : feedbackdate.trim();
    }

    public String getFeedbackreply() {
        return feedbackreply;
    }

    public void setFeedbackreply(String feedbackreply) {
        this.feedbackreply = feedbackreply == null ? null : feedbackreply.trim();
    }

    public String getReplydate() {
        return replydate;
    }

    public void setReplydate(String replydate) {
        this.replydate = replydate == null ? null : replydate.trim();
    }
}