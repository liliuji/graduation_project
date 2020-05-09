package com.service.imple;

import com.domain.Notice;
import com.mapper.NoticeMapper;
import com.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeServiceImple implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int saveNotice(Notice notice){
        return noticeMapper.saveNotice(notice);
    }

    @Override
    public List<Notice> getNoticeList(){
        return noticeMapper.getNoticeList();
    }

    @Override
    public int updateNotice(String noticeTitle,String noticeContent,int noticeId){
        return noticeMapper.updateNotice(noticeTitle,noticeContent,noticeId);
    }

    @Override
    public int deleteNotice(int noticeId){
        return noticeMapper.deleteNotice(noticeId);
    }

    @Override
    public Notice getNoticeById(int noticeId){
        return noticeMapper.getNoticeById(noticeId);
    }
}
