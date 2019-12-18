package com.iwne.dorm.info.mangement.controller;

import com.iwne.dorm.info.mangement.exception.OldPasswordIncorrectException;
import com.iwne.dorm.info.mangement.exception.UploadFileException;
import com.iwne.dorm.info.mangement.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoService userInfoService;


    @GetMapping()
    public Object getUserInfo(@RequestParam("detail")boolean detail
                                    ,@AuthenticationPrincipal UserDetails userDetails) {
        if (!detail) {
            return userInfoService.briefUserInfo(userDetails.getUsername());
        }else {
            return userInfoService.userInfo(userDetails.getUsername());
        }

    }


    @PutMapping
    public boolean updateUserInfo(@AuthenticationPrincipal UserDetails userDetails
                                 , @RequestBody Map<String, String> m) throws OldPasswordIncorrectException {
        String fn = m.getOrDefault("fn", "updatePwd");
        if (fn.equals("updatePwd")) {
            boolean result = userInfoService.updateUserPassword(userDetails.getUsername()
                    , m.get("oldPwd")
                    , m.get("nPwd"));
            if (!result) {
                throw new OldPasswordIncorrectException("旧密码不匹配");
            }
        }else if(fn.equals("all")) {
            this.userInfoService.updateUserInfoAll(userDetails.getUsername()
                    ,m.get("gender")
                    , m.get("phoneNum")
                    , m.get("roomNo"));
        }

        return true;
    }


    @PostMapping("/avatar")
    public String updateUserAvatar(@RequestParam("avatar") MultipartFile avatar) throws UploadFileException {
        return userInfoService.uploadUserAvatar(SecurityContextHolder.getContext().getAuthentication().getName()
                                                , avatar);
    }





}
