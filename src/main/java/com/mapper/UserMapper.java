package com.mapper;

import com.domain.Volunteer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.domain.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("SELECT * FROM T_USER WHERE USERNAME = #{userName} AND PASSWORD = #{passWord}")
    @ResultType(User.class)
    User getUser(@Param("userName") String userName, @Param("passWord") String passWord);

    @Update("UPDATE T_USER SET PASSWORD = #{newPassword} WHERE USERNAME = #{userName}")
    int updatePassword(@Param("userName")String userName, @Param("newPassword") String newPassword);

    @Select("SELECT * FROM T_USER WHERE USERID = #{userId}")
    @ResultType(User.class)
    User getUserByUserId(@Param("userId")int userId);

    @Select("SELECT * FROM T_USER WHERE USERTYPE = #{userType} ")
    List<Volunteer> getUserByType(@Param("userType")String userType);

    @Delete("delete from T_USER where userId = #{volunteerId}")
    int deleteVolunteerById(@Param("volunteerId")int volunteerId);
}
