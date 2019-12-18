package com.iwne.dorm.info.mangement.helper;

import com.iwne.dorm.info.mangement.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public class Helper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);


    public static String BASE64_BASE = "data:image";
    public static String BASE64_PNG = "/png;base64,";


    public static String getImgToBase64String(String path) {
        String basePath = System.getProperty("user.dir");;
        File f = new File(basePath + path);

        String fileClazz = path.substring(path.lastIndexOf(".") + 1);



        String sb = "";
        try {
            FileInputStream file = new FileInputStream(f);

            byte[] bytes = new byte[file.available()];
            file.read(bytes);
            sb = BASE64_BASE + "/"+fileClazz.toUpperCase()+";base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            LOGGER.error(e.getMessage() + ": 文件未找到");
        }
        return sb;
    }




}
