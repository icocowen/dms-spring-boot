package com.iwne.dorm.info.mangement.dao;

import com.iwne.dorm.info.mangement.model.TabStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TabStudentMapper {

    @Select("SELECT id,stu_num as stuNum,room_no as roomNo,name,gender" +
            ",phone_num as phoneNum,stu_class as stuClass,stu_coach_no as stuCoachNo,bad_num as badNum" +
            "FROM tab_student" +
            "WHERE stu_num = #{stuNum}")
    TabStudent findByStuNum(@Param("stuNum") String stuNum);


    @Select("SELECT id,stu_num as stuNum,room_no as roomNo,name,gender " +
            ",phone_num as phoneNum,stu_class as stuClass,stu_coach_no as stuCoachNo,bad_num as badNum " +
            "FROM tab_student " +
            "WHERE name = #{name}")
    TabStudent findByStuName(@Param("name") String name);


}
