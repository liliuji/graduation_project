package com.service.imple;


import com.domain.Volunteer;
import com.mapper.VolunteerMapper;
import com.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VolunteerServiceImple implements VolunteerService {

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Override
    public  int volunteerRegister(Volunteer volunteer){
        return volunteerMapper.volunteerRegister(volunteer);
    }

    @Override
    public Volunteer getVolunteer(String account, String password){
        return volunteerMapper.getVolunteer(account,password);
    }

    @Override
    public Volunteer getVolunteerByAccount(String volunteeraccount){
        return volunteerMapper.getVolunteerByAccount(volunteeraccount);
    }

    @Override
    public Volunteer getVolunteerById(int volunteerId){
        return volunteerMapper.getVolunteerById(volunteerId);
    }
}
