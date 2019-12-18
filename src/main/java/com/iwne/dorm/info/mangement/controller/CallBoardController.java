package com.iwne.dorm.info.mangement.controller;

import com.iwne.dorm.info.mangement.dao.AdaministratorMapper;
import com.iwne.dorm.info.mangement.model.TabAdministrator;
import com.iwne.dorm.info.mangement.model.TabCallBoard;
import com.iwne.dorm.info.mangement.service.CallBoardService;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("callboard")
public class CallBoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);


    @Autowired
    CallBoardService callBoardService;


    @Autowired(required = false)
    AdaministratorMapper adaministratorMapper;

    @GetMapping
    public Map<String, Object> pullTenItemWithOwn(@AuthenticationPrincipal UserDetails userDetails) {

        TabAdministrator administrator = adaministratorMapper.findByJobNum(userDetails.getUsername());
        Map<String, Object> m = new HashMap<>();
        m.put("name", administrator.getName());
        m.put("data", callBoardService.getTenItemWithOwn(administrator.getId()));
        return m;
    }


    @PostMapping
    public void createNewCallBoard(@AuthenticationPrincipal UserDetails userDetails
                                    ,@RequestBody Map<String, String> m) {
        TabAdministrator administrator = adaministratorMapper.findByJobNum(userDetails.getUsername());
        //失败就抛出异常
        callBoardService.createNewCallBoard(administrator.getId()
                                            ,m.get("title")
                                            ,m.get("date")
                                            ,m.get("desc"));
    }


    @DeleteMapping
    public void deleteCallBoardItem(@AuthenticationPrincipal UserDetails userDetails
                                    ,Integer id) {
        TabAdministrator administrator = adaministratorMapper.findByJobNum(userDetails.getUsername());
        //失败就抛出异常
        callBoardService.deleteCallBoardItem(administrator.getId(),id);
    }





}
