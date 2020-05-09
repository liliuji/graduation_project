package com.service;

import com.domain.Notice;

import java.util.List;

public interface NoticeService {
    int saveNotice(Notice notice);

    List<Notice> getNoticeList();

    int updateNotice(String noticeTitle,String noticeContent,int noticeId);

    int deleteNotice(int noticeId);

    Notice getNoticeById(int noticeId);
}
