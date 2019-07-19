package com.stevenw.mysqldao2;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author stevenw
 * @date 2019/7/15
 */
@Mapper
public interface AMapper {
    List<Map<String,Object>> listAll();
}
