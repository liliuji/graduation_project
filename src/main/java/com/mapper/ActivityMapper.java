package com.mapper;

import com.domain.Activity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityMapper {

    @Insert("insert t_activity(activityName,activityDate,activityLocation,deadline,activityStartDate,activityEndDate,demand,activityRequirement,activityContent,byUserId,activityImgPath,"
            + "releaseTime) values (#{activityname},#{activitydate},#{activitylocation},#{deadline},#{activitystartdate},#{activityenddate},#{demand},#{activityrequirement},"
            + "#{activitycontent},#{byuserid},#{activityimgpath},#{releasetime})")
    int saveActivity(Activity activity);

    @Select("select * from t_activity where byUserId = #{userId}")
    List<Activity> getActivityByUserId(@Param("userId") int userId);

    @Select("select * from t_activity where activityId = #{activityId}")
    Activity getActivityById(@Param("activityId") int activityId);

    @Delete("delete from t_activity where activityId = #{activityId}")
    int deleteActivityById(@Param("activityId")int activityId);

    @Select("select * from t_activity")
    List<Activity> getActivityList();

    @Select("select * from t_activity where activityName = #{activename}")
    Activity getActivityByName(@Param("activename") String activename);
}