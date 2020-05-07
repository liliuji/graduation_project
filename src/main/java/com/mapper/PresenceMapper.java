package com.mapper;

import com.domain.Presence;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PresenceMapper {

    @Insert("insert into t_presence (presenceName, presenceContent,volunteerName, activeName,presenceImgPath)"
            + "values (#{presencename,jdbcType=VARCHAR},#{presencecontent,jdbcType=VARCHAR},"
            + " #{volunteername,jdbcType=VARCHAR},#{activename,jdbcType=VARCHAR},#{presenceimgpath,jdbcType=VARCHAR})")
    int savePresence(Presence presence);

    @Select("select * from t_presence")
    List<Presence> getPresenceList();

    @Update("update t_presence set presenceName = #{presencename},presenceContent=#{presencecontent},volunteerName=#{volunteername},"
            + "activeName=#{activename} where presenceId = #{presenceid}")
    int updatePresence(Presence presence);

    @Delete("delete from t_presence where presenceId = #{presenceId}")
    int deletePresence(@Param("presenceId") int presenceId);
}