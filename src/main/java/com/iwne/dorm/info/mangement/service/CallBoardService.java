package com.iwne.dorm.info.mangement.service;

import com.alibaba.fastjson.JSON;
import com.iwne.dorm.info.mangement.dao.TabCallBoardMapper;
import com.iwne.dorm.info.mangement.model.TabCallBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CallBoardService {

    @Autowired(required = false)
    TabCallBoardMapper tabCallBoardMapper;


    public List<TabCallBoard> getTenItemWithOwn(Integer adminId) {
        List<TabCallBoard> boards = tabCallBoardMapper.findByAdminId(adminId);

        return boards;
    }

    public void createNewCallBoard(Integer id, String title, String date, String desc) {
        Map<String, String> m = new HashMap<>();
        m.put("title", title);
        m.put("content", desc);
        tabCallBoardMapper.createNewCallBoard(id,  date, JSON.toJSONString(m));
    }

    public void deleteCallBoardItem(Integer administratorId, Integer callBoardId) {
        tabCallBoardMapper.deleteCallBoardItem(administratorId, callBoardId);
    }
}
