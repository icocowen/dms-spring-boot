package com.iwne.dorm.info.mangement.dao;

import com.iwne.dorm.info.mangement.model.TabIllegalRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TabIllegalRecordMapper {

    @Select("SELECT ti.id , ti.in_date as inDate,ti.admini_id as adminiId, ta.name as adminName " +
            ",ti.remark,ti.stu_num as stuNum,ts.name as stuName " +
            "FROM tab_illegal_record as ti, tab_student as ts , tab_administrator as ta " +
            "WHERE ti.stu_num = ts.stu_num and ti.admini_id = ta.id " +
            "ORDER BY ti.in_date desc " +
            "LIMIT #{pageIndex}, #{pageItem}")
    List<Map<String ,Object>> findByPageIndex(@Param("pageIndex")Integer pageIndex, @Param("pageItem") Integer pageItem);

    @Select("SELECT count(id) from tab_illegal_record")
    Integer getItemTotal();



    @Select("SELECT ti.id , ti.in_date as inDate,ti.admini_id as adminiId, ta.name as adminName " +
            ",ti.remark,ti.stu_num as stuNum,ts.name as stuName " +
            "FROM tab_illegal_record as ti, tab_student as ts , tab_administrator as ta " +
            "WHERE ti.stu_num = ts.stu_num and ti.admini_id = ta.id and ti.stu_num = #{stuNum} " +
            "ORDER BY ti.in_date desc " +
            "LIMIT #{pageIndex}, #{pageItem}")
    List<Map<String ,Object>> findByStuNum(@Param("stuNum")String stuNum, @Param("pageIndex")Integer pageIndex, @Param("pageItem") Integer pageItem);

    @Select("SELECT count(id) from tab_illegal_record WHERE stu_num = #{stuNum}")
    Integer findByStuNumTotal(@Param("stuNum")String stuNum);

    @Delete("DELETE FROM tab_illegal_record WHERE id = #{itemId}")
    void deleteItemWithItemId(@Param("itemId") String itemId);
}
