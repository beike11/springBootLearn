package com.stevenw.mysqldao1;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author stevenw
 * @date 2019/7/15
 */
@Mapper
public interface UserInfoMapper {
    List<Map<String,Object>> listAll();
 }
