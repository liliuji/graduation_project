package com.service.imple;

import com.domain.Enlist;
import com.mapper.EnlistMapper;
import com.service.EnlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnlistServiceImple implements EnlistService {

    @Autowired
    private EnlistMapper enlistMapper;

    @Override
    public List<Enlist> getEnlists() {
        return enlistMapper.getEnlists();
    }

    @Override
    public int updateEnlistStatus(String enlistStatus,int enlistId){
        return enlistMapper.updateEnlistStatus(enlistStatus,enlistId);
    }

    @Override
    public int deleteEnlistById(int enlistId){
        return enlistMapper.deleteEnlistById(enlistId);
    }

    @Override
    public int saveEnlist(Enlist enlist){
        return enlistMapper.saveEnlist(enlist);
    }

    @Override
    public String getEnlistStatus(Integer activityid, Integer userId){
        return enlistMapper.getEnlistStatus(activityid,userId);
    }

    @Override
    public List<Enlist> getEnlistsByActivityId(int activityId){
        return enlistMapper.getEnlistsByActivityId(activityId);
    }

    @Override
    public List<Enlist> getEnlistsByVolunteerId(int volunteerId){
        return enlistMapper.getEnlistsByVolunteerId(volunteerId);
    }

    @Override
    public int getVolunteerCount(Integer activityid){
        return enlistMapper.getVolunteerCount(activityid);
    }
}
