package com.service;


import com.domain.Volunteer;

public interface VolunteerService {

    int volunteerRegister(Volunteer volunteer);

    Volunteer getVolunteer(String account, String password);

    Volunteer getVolunteerByAccount(String volunteeraccount);

    Volunteer getVolunteerById(int volunteerId);
}
