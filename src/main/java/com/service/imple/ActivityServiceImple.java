package com.service.imple;

import com.domain.Activity;
import com.mapper.ActivityMapper;
import com.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityServiceImple implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveActivity(Activity activity) {

        return activityMapper.saveActivity(activity);
    }

    @Override
    public List<Activity> getActivityByUserId(int userId){
        return activityMapper.getActivityByUserId(userId);
    }

    @Override
    public Activity getActivityById(int activityId){
        return activityMapper.getActivityById(activityId);
    }

    @Override
    public int deleteActivityById(int activityId){
        return activityMapper.deleteActivityById(activityId);
    }
}
