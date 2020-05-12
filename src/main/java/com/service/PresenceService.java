package com.service;


import com.domain.Presence;

import java.util.List;

public interface PresenceService {

    int savePresence(Presence presence);

    List<Presence> getPresenceList();

    int updatePresence(Presence presence);

    int deletePresence(int presenceId);

    Presence getPresenceById(int presenceId);

    List<Presence> getPresenceListByActivity(String activityname);

    List<Presence> getPresenceListByVolunteerAccount(String account);
}
