package com.iwne.dorm.info.mangement.dao;

import com.iwne.dorm.info.mangement.model.TabAdministrator;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
public interface AdaministratorMapper {

    @Select("Select id,name,job_num,avatar, salt,phone_num,password,room_no,gender from tab_administrator where job_num = #{job_num}")
    @Results({
            @Result(property = "jobNum", column = "job_num"),
            @Result(property = "phoneNum", column = "phone_num"),
            @Result(property = "roomNo", column = "room_no")
    })
    TabAdministrator findByJobNum(@Param("job_num")String jobNum);


    @Select("Select id,name,job_num,avatar, salt,phone_num,password,room_no,gender from tab_administrator where id = #{id}")
    @Results({
            @Result(property = "jobNum", column = "job_num"),
            @Result(property = "phoneNum", column = "phone_num"),
            @Result(property = "roomNo", column = "room_no")
    })
    TabAdministrator findById(@Param("id")String id);




    @Update("UPDATE tab_administrator set salt = #{salt} where job_num = #{job_num}")
    void updateSalt(@Param("job_num")String jobNum, @Param("salt") String salt);

    @Update("UPDATE tab_administrator set salt = null where job_num = #{job_num}")
    void removeSalt(@Param("job_num")String jobNum);

    @Update("UPDATE tab_administrator set password = #{nPassword} where job_num = #{job_num}")
    void updatePassword(@Param("job_num")String jobNum, @Param("nPassword")String nPassword);

    @Update("UPDATE tab_administrator set gender = #{gender},phone_num = #{phoneNum},room_no = #{roomNo}  where job_num = #{job_num}")
    void updateUserAllInfo(@Param("job_num") String job_num,@Param("gender") int gender,@Param("phoneNum") String phoneNum, @Param("roomNo")String roomNo);

    @Update("UPDATE tab_administrator set avatar = #{path} where job_num = #{job_num}")
    void updateAvatar(@Param("job_num")String jobNum, @Param("path")String path);
}
