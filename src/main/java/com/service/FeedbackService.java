package com.service;


import com.domain.Feedback;

import java.util.List;

public interface FeedbackService {

    int updateFeedback(String feedbackReply, String replyDate, int feedbackId);

    int deleteFeeback( int feedbackId);

    List<Feedback> getFeedbackList();
}
