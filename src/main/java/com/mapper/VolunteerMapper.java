package com.mapper;


import com.domain.Volunteer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerMapper {

    @Insert("insert T_USER(account,password,userName,sex,age,userTel,card,createDate,userType) "
            + "values(#{account},#{password},#{userName},#{sex},#{age},#{userTel},#{card},#{createDate},#{userType,jdbcType=VARCHAR})")
    int volunteerRegister(Volunteer volunteer);

    @Select("SELECT * FROM T_USER WHERE ACCOUNT = #{account} AND PASSWORD = #{passWord}")
    Volunteer getVolunteer(@Param("account") String account, @Param("passWord") String password);

    @Select("SELECT * FROM T_USER WHERE ACCOUNT = #{volunteeraccount}")
    Volunteer getVolunteerByAccount(@Param("volunteeraccount") String volunteeraccount);

    @Select("SELECT * FROM T_USER WHERE userId = #{volunteerId}")
    Volunteer getVolunteerById(@Param("volunteerId") int volunteerId);
}
