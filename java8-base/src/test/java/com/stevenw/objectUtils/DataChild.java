package com.stevenw.objectUtils;

import java.io.Serializable;

/**
 * @author stevenw
 * @date 2019/11/19
 */
public class DataChild implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
