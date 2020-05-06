package com.mapper;

import com.domain.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NoticeMapper {

    @Insert("insert into t_notice (noticeTitle,noticeContent,noticeCreateDate)"
            + " values (#{noticetitle},#{noticecontent},#{noticecreatedate})")
    int saveNotice(Notice notice);

    @Select("select * from t_notice")
    List<Notice> getNoticeList();

    @Update("update t_notice set noticeTitle = #{noticeTitle},noticeContent = #{noticeContent} where noticeId = #{noticeId}")
    int updateNotice(@Param("noticeTitle")String noticeTitle,@Param("noticeContent")String noticeContent,@Param("noticeId")int noticeId);

    @Delete("delete from t_notice where noticeId = #{noticeId}")
    int deleteNotice(@Param("noticeId") int noticeId);
}