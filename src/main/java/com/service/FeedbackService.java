package com.service;


import com.domain.Feedback;

import java.util.List;

public interface FeedbackService {

    int updateFeedback(Feedback feedback);

    int deleteFeeback( int feedbackId);

    List<Feedback> getFeedbackList();
}
