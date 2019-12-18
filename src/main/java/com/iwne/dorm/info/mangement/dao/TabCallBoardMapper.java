package com.iwne.dorm.info.mangement.dao;

import com.iwne.dorm.info.mangement.model.TabCallBoard;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TabCallBoardMapper {

    @Select("SELECT id, in_date, show_date, admini_id, content " +
            "FROM tab_call_board " +
            "WHERE admini_id = #{admini_id} " +
            "order by in_date desc " +
            "limit 10")
    @Results({
            @Result(property = "inDate", column = "in_date"),
            @Result(property = "showDate", column = "show_date"),
            @Result(property = "adminiId", column = "admini_id")
    })
    public List<TabCallBoard> findByAdminId(@Param("admini_id") Integer adminId);

    @Insert("INSERT INTO tab_call_board(in_date,show_date,admini_id,content)" +
            " values(now(), #{date},#{admini_id}, #{content})")
    void createNewCallBoard(@Param("admini_id") Integer id, @Param("date") String date, @Param("content")String content);

    @Delete("DELETE FROM tab_call_board WHERE admini_id = #{admini_id} and id = #{id} ")
    void deleteCallBoardItem(@Param("admini_id") Integer administratorId,@Param("id") Integer callBoardId);
}
