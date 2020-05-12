package com.service;

import com.domain.Activity;

import java.util.List;
public interface ActivityService {
    int saveActivity(Activity activity);

    List<Activity> getActivityByUserId(int userId);

    Activity getActivityById(int activityId);

    int deleteActivityById(int activityId);

    List<Activity> getActivityList();

    Activity getActivityByName(String activename);
}
