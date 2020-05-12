package com.service.imple;

import com.domain.Presence;
import com.mapper.PresenceMapper;
import com.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PresenceServiceImple implements PresenceService {

    @Autowired
    private PresenceMapper presenceMapper;

    @Override
    public int savePresence(Presence presence){
        return presenceMapper.savePresence(presence);
    }

    @Override
    public List<Presence> getPresenceList(){
        return presenceMapper.getPresenceList();
    }

    @Override
    public int updatePresence(Presence presence){
        return presenceMapper.updatePresence(presence);
    }

    @Override
    public int deletePresence(int presenceId){
        return presenceMapper.deletePresence(presenceId);
    }

    @Override
    public Presence getPresenceById(int presenceId){
        return presenceMapper.getPresenceById(presenceId);
    }

    @Override
    public  List<Presence> getPresenceListByActivity(String activityname){
        return presenceMapper.getPresenceListByActivity(activityname);
    }

    @Override
    public List<Presence> getPresenceListByVolunteerAccount(String account){
        return presenceMapper.getPresenceListByVolunteerAccount(account);
    }
}

