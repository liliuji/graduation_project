package com.mapper;

import com.domain.Presence;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PresenceMapper {

    @Insert("insert into t_presence (presenceName, presenceContent,volunteerId, activeId)"
            + "values (#{presencename,jdbcType=VARCHAR},#{presencecontent,jdbcType=VARCHAR},"
            + " #{volunteerid,jdbcType=INTEGER},#{activeid,jdbcType=INTEGER})")
    int savePresence(Presence presence);

    @Select("select * from t_presence")
    List<Presence> getPresenceList();

    int updatePresence(Presence presence);

    @Delete("delete from t_presence where presenceId = #{presenceId}")
    int deletePresence(@Param("presenceId") int presenceId);
}