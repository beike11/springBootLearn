package com.stevenw.io;

import java.util.Map;

/**
 * @author stevenw
 * @date 2020/4/10
 */
public class FileInfo {
    private Integer id;
    private String filePath;
    private String fileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
