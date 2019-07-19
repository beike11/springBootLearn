package com.stevenw.service;

import com.stevenw.mysqldao1.UserInfoMapper;
import com.stevenw.mysqldao2.AMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author stevenw
 * @date 2019/7/15
 */
@Service
public class TestServieImpl implements TestService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    AMapper aMapper;

    @Override
    public List<Map<String, Object>> listAllOne() {
        return userInfoMapper.listAll();
    }

    @Override
    public List<Map<String, Object>> listAllTwo() {
        return aMapper.listAll();
    }
}
