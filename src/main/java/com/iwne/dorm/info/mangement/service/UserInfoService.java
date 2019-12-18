package com.iwne.dorm.info.mangement.service;

import com.iwne.dorm.info.mangement.controller.UserInfoController;
import com.iwne.dorm.info.mangement.dao.AdaministratorMapper;
import com.iwne.dorm.info.mangement.exception.UploadFileException;
import com.iwne.dorm.info.mangement.helper.Helper;
import com.iwne.dorm.info.mangement.model.TabAdministrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class UserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);

    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    AdaministratorMapper adaministratorMapper;

    public UserInfoService() {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public Map<String, String> briefUserInfo(String username) {
        TabAdministrator admin = adaministratorMapper.findByJobNum(username);
        Map<String, String> m = new HashMap<>();
        m.put("avatar" , Helper.getImgToBase64String(admin.getAvatar()));
        m.put("name", admin.getName());
        return m;
    }


    public Map<String, String> userInfo(String username) {
        TabAdministrator administrator = adaministratorMapper.findByJobNum(username);
        Map<String, String> m = new HashMap<>();
        m.put("name", administrator.getName());
        m.put("avatar", Helper.getImgToBase64String(administrator.getAvatar()));
        m.put("jobNum", administrator.getJobNum());
        m.put("phoneNum", administrator.getPhoneNum());
        m.put("roomNo", administrator.getRoomNo());
        m.put("gender", administrator.getGender() ? "male": "female");
        return m;
    }


    public boolean updateUserPassword(String username,String oldPassword,  String newPassword) {
        TabAdministrator administrator = adaministratorMapper.findByJobNum(username);
        if (this.passwordEncoder.matches(oldPassword, administrator.getPassword())) {
            adaministratorMapper.removeSalt(username);
            adaministratorMapper.updatePassword(username, this.passwordEncoder.encode(newPassword));
            return true;
        }
       return false;
    }

    public boolean updateUserInfoAll(String username, String gender, String phoneNum, String roomNum) {
        adaministratorMapper.updateUserAllInfo(username, gender.equals("male")? 1 : 0, phoneNum, roomNum);
        return true;
    }

    public String uploadUserAvatar(String username, MultipartFile file) throws UploadFileException {
        List<String> imageType = Arrays.asList("jpg","jpeg", "png", "bmp", "gif");
        // 获取文件名，带后缀
        String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀格式
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        if (imageType.contains(fileSuffix)) {
            // 只有当满足图片格式时才进来，重新赋图片名，防止出现名称重复的情况
            String newFileName = UUID.randomUUID()+ originalFilename;
            // 该方法返回的为当前项目的工作目录，即在哪个地方启动的java线程
            String dirPath = System.getProperty("user.dir");
            String path = File.separator + "useravatar" + File.separator + newFileName;
            File destFile = new File(dirPath + path);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }

            try {
                byte[] b = Arrays.copyOf(file.getBytes(), file.getBytes().length);
                file.transferTo(destFile);
                // 将相对路径返回给前端
                adaministratorMapper.updateAvatar(username, path);


                return Helper.BASE64_BASE  + "/"+ fileSuffix.toUpperCase()+";base64,"  + Base64.getEncoder().encodeToString(b);
            } catch (IOException e) {
                LOGGER.error("upload pic error: " + e);
                return null;
            }
        } else {
            // 非法文件
            LOGGER.error("the picture's suffix is illegal");
            throw new UploadFileException("上传非法文件");
        }
    }
}
