package com.iwne.dorm.info.mangement.dao;

import com.alibaba.fastjson.JSONObject;
import com.iwne.dorm.info.mangement.DormInfoMangementSystemApplication;
import com.iwne.dorm.info.mangement.helper.Helper;
import com.iwne.dorm.info.mangement.model.TabAdministrator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DormInfoMangementSystemApplication.class})// 指定启动类
public class AdaministratorMapperTest {

    @Autowired(required = false)
    AdaministratorMapper adaministratorMapper;


    @Autowired(required = false)
    TabIllegalRecordMapper tabIllegalRecordMapper;

    @Test
    public void testFindById() {
        TabAdministrator byId = adaministratorMapper.findByJobNum("123456");
        System.out.println(byId);
    }


    @Test
    public void testUpdate() {
        adaministratorMapper.updateAvatar("123456","你哈");

    }


    @Test
    public void testEncode() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }

    @Test
    public void testUidir() {
        System.out.println(System.getProperty("user.dir"));
    }


    @Test
    public void testHeple() {
        System.out.println(Helper.getImgToBase64String("/useravatar/a.png"));
    }


    @Test
    public void testIllMapper() {
        List<Map<String, Object>> byPageIndex = tabIllegalRecordMapper.findByPageIndex(0, 7);
        for (Map<String, Object> pageIndex : byPageIndex) {
            System.out.println(JSONObject.toJSONString(pageIndex));
        }
    }
}
