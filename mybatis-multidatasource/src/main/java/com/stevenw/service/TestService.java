package com.stevenw.service;

import com.stevenw.mysqldao1.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author stevenw
 * @date 2019/7/15
 */
public interface TestService {
    List<Map<String,Object>> listAllOne();
    List<Map<String,Object>> listAllTwo();

}
