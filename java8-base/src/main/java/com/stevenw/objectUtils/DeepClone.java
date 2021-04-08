package com.stevenw.objectUtils;

import java.io.*;

/**
 * @author stevenw
 * @date 2019/11/19
 */
public class DeepClone {
    public static <T extends Serializable> T clone(T t){
        T cloneObject = null;

        try {
            //输出缓冲区
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream(1024);
            //输出流
            ObjectOutputStream oops = new ObjectOutputStream(outBuffer);
            oops.writeObject(t);
            oops.close();
            //输入缓冲区
            ByteArrayInputStream inBuffer = new ByteArrayInputStream(outBuffer.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(inBuffer);
            cloneObject = (T)ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObject;
    }
}
