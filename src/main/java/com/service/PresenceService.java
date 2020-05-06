package com.service;


import com.domain.Presence;

import java.util.List;

public interface PresenceService {

    int savePresence(Presence presence);

    List<Presence> getPresenceList();

    int updatePresence(Presence presence);

    int deletePresence(int presenceId);
}
