package com.service;


import com.domain.Enlist;

import java.util.List;

public interface EnlistService {

    List<Enlist> getEnlists();

    int updateEnlistStatus(String enlistStatus,int enlistId);

    int deleteEnlistById(int enlistId);

    int saveEnlist(Enlist enlist);

    String getEnlistStatus(Integer activityid, Integer userId);

    List<Enlist> getEnlistsByActivityId(int activityId);

    List<Enlist> getEnlistsByVolunteerId(int volunteerId);

    int getVolunteerCount(Integer activityid);
}
