package com.stevenw.io;

import org.thymeleaf.util.ArrayUtils;
import org.thymeleaf.util.ListUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author stevenw
 * @date 2020/4/7
 */
public class ZipCompressor {
    static final int BUFFER = 1024;

    private File zipFile;

    public ZipCompressor(String pathName) {
        zipFile = new File(pathName);
    }
    public void compress(Map<String,String> pathName,String... baseDir) {
        ZipOutputStream out = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
                    new CRC32());
            out = new ZipOutputStream(cos);
            for (int i = 0; i < baseDir.length; i++) {
                if(null != pathName.get(baseDir[i])){
                    File file = new File(pathName.get(baseDir[i]));
                    compress(file, out, baseDir[i]+"/","");
                }else {
                    compressEmptyDirectory(out, baseDir[i]+"/");
                }
            }
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
  /*  public void compress(String srcPathName) {
        File file = new File(srcPathName);
        if (!file.exists())
            throw new RuntimeException(srcPathName + "不存在！");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
                    new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = "";
            compress(file, out, basedir);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    private void compress(File file, ZipOutputStream out, String basedir, String fileName) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            System.out.println("压缩：" + basedir + fileName);
            this.compressDirectory(file, out, basedir, fileName);
        } else {
            System.out.println("压缩：" + basedir + fileName);
            this.compressFile(file, out, basedir, fileName);
        }
    }

    /** 压缩一个目录 */
    private void compressDirectory(File dir, ZipOutputStream out, String basedir,String fileName) {
        if (!dir.exists())
            return;

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            /* 递归 */
            compress(files[i], out, basedir + dir.getName() + "/", fileName);
        }
    }

    /** 压缩一个文件 */
    private void compressFile(File file, ZipOutputStream out, String basedir, String fileName) {
        try {
            String filePath = basedir + fileName;
            ZipEntry entry = new ZipEntry(filePath);
            out.putNextEntry(entry);
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 压缩一个空文件夹 */
    private void compressEmptyDirectory(ZipOutputStream out, String basedir) {
        try {
            ZipEntry entry = new ZipEntry(basedir);
            out.putNextEntry(entry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("1","C:/Users/stevenw/Desktop/发布/1.txt");
        map.put("2","C:/Users/stevenw/Desktop/发布/2.txt");
        map.put("3","C:/Users/stevenw/Desktop/发布/3.txt");
        map.put("4",null);
        ZipCompressor zc = new ZipCompressor("C:/Users/stevenw/Desktop/发布/1.zip");
        zc.compress(map,"1","2","3","4");
    }
}
