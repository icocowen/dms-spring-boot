package com.iwne.dorm.info.mangement.controller;

import com.iwne.dorm.info.mangement.service.IllegalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// TODO: 12/19/2019 用切面捕捉全部异常信息
@RestController
@RequestMapping("illegalrecode")
public class IllegalRecodeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired(required = false)
    IllegalRecordService illegalRecordService;

    @GetMapping
    public Object getOnePageItem(
            @RequestParam(value = "fn", required = false) String fn
            ,@RequestParam(value = "pgIdx", required = false) Integer pgIndex
            ,@RequestParam(value = "stuName", required = false) String stuName) {
        if (StringUtils.isEmpty(fn)) {
            LOGGER.info("获取 IllegalRecodeController#getOnePageItem pgIdex: " + pgIndex);
            return illegalRecordService.getOnePageItem(pgIndex);
        }else if (fn.equals("total")) {
            LOGGER.info("获取 IllegalRecodeController#getOnePageItem TOTAL ");
            return illegalRecordService.getItemTotal();
        }else if (fn.equals("search")) {
            LOGGER.info("获取 IllegalRecodeController#getOnePageItem search, pgIdex:" + pgIndex);
            return illegalRecordService.searchStuRecord(stuName, pgIndex);
        }else if (fn.equals("searchTotal")) {
            return illegalRecordService.searchStuRecordTotal(stuName);
        }
        return null;
    }


    @DeleteMapping
    public void deleteItemWithItemId(@RequestParam("itemId") String itemId) {
        illegalRecordService.deleteItemWithItemId(itemId);
    }

}
