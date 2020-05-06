package com.service.imple;

import com.domain.Volunteer;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.User;
import com.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImple implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String userName, String passWord) {

        return userMapper.getUser(userName, passWord);
    }

    @Override
    public int updatePassword(String userName, String newPassword) {
        return userMapper.updatePassword(userName,newPassword);
    }

    @Override
    public User getUserByUserId(int userId){
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public List<Volunteer> getUserByType(String userType) {
        return userMapper.getUserByType(userType);
    }

    @Override
    public int deleteVolunteerById(int volunteerId){
        return userMapper.deleteVolunteerById(volunteerId);
    }
}
