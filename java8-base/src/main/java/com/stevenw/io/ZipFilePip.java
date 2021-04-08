package com.stevenw.io;

import org.springframework.util.StringUtils;
import org.thymeleaf.util.ListUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.FileNameMap;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author stevenw
 * @date 2020/4/10
 */
public class ZipFilePip {
    private File zipFile;
    private static final int BUFFER = 8192;
    public ZipFilePip(String pathName) {
        this.zipFile = new File(pathName);
    }

    public void zipFilePip(List<FileInfo> list) {

        long beginTime = System.currentTimeMillis();

        try (WritableByteChannel out = Channels.newChannel(new FileOutputStream(zipFile))) {

            Pipe pipe = Pipe.open();

            //异步压缩

            CompletableFuture.runAsync(() -> runTask(pipe, list));

            //获取read通道

            ReadableByteChannel readableByteChannel = pipe.source();

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER);

            while (readableByteChannel.read(buffer) >= 0) {

                buffer.flip();

                out.write(buffer);

                buffer.clear();
            }
            System.out.println(System.currentTimeMillis() - beginTime);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void runTask(Pipe pipe, List<FileInfo> list) {

        try (ZipOutputStream zos = new ZipOutputStream(Channels.newOutputStream(pipe.sink()));

             WritableByteChannel out = Channels.newChannel(zos)) {

            System.out.println("Begin");

            for (int i = 0; i < list.size(); i++) {
                FileInfo fileInfo = list.get(i);
                if (StringUtils.isEmpty(fileInfo.getFileName())) {
                    zos.putNextEntry(new ZipEntry(fileInfo.getId() + "/"));
                    continue;
                } else {
                    zos.putNextEntry(new ZipEntry(fileInfo.getId() + "/" + fileInfo.getFileName()));
                }
                File file = new File(fileInfo.getFilePath());
                FileChannel fileChannel = new FileInputStream(file).getChannel();
                fileChannel.transferTo(0, file.length(), out);
                fileChannel.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileInfo fileInfo = new FileInfo();
        FileInfo fileInfo1 = new FileInfo();
        FileInfo fileInfo2 = new FileInfo();
        FileInfo fileInfo3 = new FileInfo();
        fileInfo.setId(1);
        fileInfo.setFileName("test1.txt");
        fileInfo.setFilePath("C:/Users/stevenw/Desktop/发布/1.txt");
        fileInfo1.setId(2);
        fileInfo1.setFileName("test2.txt");
        fileInfo1.setFilePath("C:/Users/stevenw/Desktop/发布/2.txt");
        fileInfo2.setId(2);
        fileInfo2.setFileName("Docker Desktop Installer.exe");
        fileInfo2.setFilePath("D:/常用/Docker Desktop Installer.exe");
        fileInfo3.setId(3);
        List<FileInfo> fileInfos = null;
        System.out.println(ListUtils.isEmpty(fileInfos));
//        fileInfos.add(fileInfo);
//        fileInfos.add(fileInfo1);
//        fileInfos.add(fileInfo2);
//        fileInfos.add(fileInfo3);
//        ZipFilePip zipFilePip = new ZipFilePip("C:/Users/stevenw/Desktop/发布/1.zip");
//        zipFilePip.zipFilePip(fileInfos);
    }
}
