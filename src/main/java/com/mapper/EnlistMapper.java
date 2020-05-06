package com.mapper;

import com.domain.Enlist;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EnlistMapper {

    @Select("select * from t_enlist")
    List<Enlist> getEnlists();

    @Update("update t_enlist set enlistStatus = #{enlistStatus} where enlistId = #{enlistId}")
    int updateEnlistStatus(@Param("enlistStatus") String enlistStatus,@Param("enlistId")int enlistId);

    @Delete("delete from t_enlist where enlistId = #{enlistId}")
    int deleteEnlistById(int enlistId);
}