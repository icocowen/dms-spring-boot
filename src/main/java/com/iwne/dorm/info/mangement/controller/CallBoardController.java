package com.iwne.dorm.info.mangement.controller;

import com.iwne.dorm.info.mangement.dao.AdaministratorMapper;
import com.iwne.dorm.info.mangement.model.TabAdministrator;
import com.iwne.dorm.info.mangement.service.CallBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// TODO: 12/19/2019 用切面捕捉全部异常信息
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
        LOGGER.info("pullTenItemWithOwn success");
        return m;
    }


    @PostMapping
    public void createNewCallBoard(@AuthenticationPrincipal UserDetails userDetails
                                    ,@RequestBody Map<String, String> m) {
        TabAdministrator administrator = adaministratorMapper.findByJobNum(userDetails.getUsername());
        // TODO: 12/19/2019   失败就抛出异常
        callBoardService.createNewCallBoard(administrator.getId()
                                            ,m.get("title")
                                            ,m.get("date")
                                            ,m.get("desc"));
        LOGGER.info("createNewCallBoard success  params: {"+ m +"}");
    }


    @DeleteMapping
    public void deleteCallBoardItem(@AuthenticationPrincipal UserDetails userDetails
                                    ,Integer id) {
        TabAdministrator administrator = adaministratorMapper.findByJobNum(userDetails.getUsername());
        // TODO: 12/19/2019    失败就抛出异常
        callBoardService.deleteCallBoardItem(administrator.getId(),id);
        LOGGER.info("deleteCallBoardItem success id :" + id);

    }







}
