package com.mapper;

import com.domain.Feedback;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FeedbackMapper {

    @Update("update t_feedback set feedbackReply = #{feedbackReply},replyDate = #{replyDate} where feedbackId = #{feedbackId}")
    int updateFeedback(@Param("feedbackReply") String feedbackReply, @Param("replyDate") String replyDate, @Param("feedbackId") int feedbackId);

    @Delete("delete from t_feedback where feedbackId = #{feedbackId}")
    int deleteFeeback(@Param("feedbackId") int feedbackId);

    @Select("select * from t_feedback")
    List<Feedback> getFeedbackList();
}