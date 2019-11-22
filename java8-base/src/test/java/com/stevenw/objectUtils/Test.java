package com.stevenw.objectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author stevenw
 * @date 2019/11/19
 */

public class Test {
    @org.junit.Test
    public void testCloneObjct(){
        DataChild dataChild = new DataChild();
        dataChild.setId(1);
        dataChild.setName("test1");
        Data data = new Data();
        data.setId(0);
        data.setName("test");
        data.setTime(new Date());
        data.setDataChild(dataChild);
        Data data1 = DeepClone.clone(data);
        data1.setName("end");
        data1.setId(3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            data1.setTime(simpleDateFormat.parse("2019-07-03"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data1.getDataChild().setId(100);
        data1.getDataChild().setName("test5");
        System.out.println(data.getName());
        System.out.println(data1.getName());
    }
}
