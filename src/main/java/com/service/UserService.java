package com.service;

import com.domain.User;
import com.domain.Volunteer;

import java.util.List;

public interface UserService {

    User getUser(String userName, String passWord);

    int updatePassword(String userName, String newPassword);

    User getUserByUserId(int userId);

    List<Volunteer> getUserByType(String userType);

    int deleteVolunteerById(int volunteerId);
}
