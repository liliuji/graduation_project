package com.service.imple;

import com.domain.Feedback;
import com.mapper.FeedbackMapper;
import com.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FeedbackServiceImple implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public int updateFeedback(String feedbackReply, String replyDate, int feedbackId) {
        return feedbackMapper.updateFeedback(feedbackReply,replyDate,feedbackId);
    }

    @Override
    public int deleteFeeback(int feedbackId){
        return feedbackMapper.deleteFeeback(feedbackId);
    }

    @Override
    public List<Feedback> getFeedbackList(){
        return feedbackMapper.getFeedbackList();
    }
}
