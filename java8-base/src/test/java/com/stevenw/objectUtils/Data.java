package com.stevenw.objectUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stevenw
 * @date 2019/11/19
 */
public class Data implements Serializable {
    private static ThreadLocal<Data> local = new ThreadLocal<Data>(){
        public Data initialValue(Data data){
            return data;
        }
    };

    private Integer id;
    private Date time;
    private String name;
    private DataChild dataChild;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataChild getDataChild() {
        return dataChild;
    }

    public void setDataChild(DataChild dataChild) {
        this.dataChild = dataChild;
    }
}
