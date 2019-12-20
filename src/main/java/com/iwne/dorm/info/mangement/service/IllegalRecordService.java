package com.iwne.dorm.info.mangement.service;

import com.iwne.dorm.info.mangement.dao.AdaministratorMapper;
import com.iwne.dorm.info.mangement.dao.TabIllegalRecordMapper;
import com.iwne.dorm.info.mangement.dao.TabStudentMapper;
import com.iwne.dorm.info.mangement.model.TabIllegalRecord;
import com.iwne.dorm.info.mangement.model.TabStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


// TODO: 12/19/2019 日志记录
@Service
public class IllegalRecordService {

    @Autowired(required = false)
    TabIllegalRecordMapper tabIllegalRecordMapper;

    @Autowired(required = false)
    TabStudentMapper tabStudentMapper;
//
//    @Autowired(required = false)
//    AdaministratorMapper adaministratorMapper;

    private static final int ONE_PAGE_ITEM_NUM = 7; //一页item数量


    public List<Map<String, Object>> getOnePageItem(int pageIndex) {
        return tabIllegalRecordMapper.findByPageIndex(ONE_PAGE_ITEM_NUM * (pageIndex - 1), ONE_PAGE_ITEM_NUM);
    }

    public Integer getItemTotal() {
        return tabIllegalRecordMapper.getItemTotal();
    }


    public List<Map<String, Object>> searchStuRecord(String stuName, int pageIndex) {
        TabStudent stu = tabStudentMapper.findByStuName(stuName);

        return tabIllegalRecordMapper.findByStuNum(stu.getStuNum(),
        ONE_PAGE_ITEM_NUM * (pageIndex - 1), ONE_PAGE_ITEM_NUM);
    }

    public Integer searchStuRecordTotal(String stuName) {
        TabStudent stu = tabStudentMapper.findByStuName(stuName);

        return tabIllegalRecordMapper.findByStuNumTotal(stu.getStuNum());
    }

    public void deleteItemWithItemId(String itemId) {
        tabIllegalRecordMapper.deleteItemWithItemId(itemId);
    }
}
