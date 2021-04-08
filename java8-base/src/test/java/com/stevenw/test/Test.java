package com.stevenw.test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author stevenw
 * @date 2019/11/26
 */
public class Test {
    @org.junit.Test
    public void doTest() {
        String a = "433dfffieeifeiifeieff\n222我";
        InputStream bis = new ByteArrayInputStream(a.getBytes());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(bis, 10);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(new File("D:\\Temp\\123.txt"));
            BufferedOutputStream bos = new BufferedOutputStream(outputStream, 10);
            int len;
            while ((len = bufferedInputStream.read()) != -1) {
                System.out.println(len);
                bos.write(len);
            }

            bos.flush();
            bos.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }


    public void test2() {

    }

    public void getN(int n) {
        // 1,  1,   2,    3      5,           8,            13,       21,              34, 55

    }
}
