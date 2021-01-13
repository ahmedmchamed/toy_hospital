package com.toyhospital.queryapp.back_end.models;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class FileTest {

    private List<MultipartFile>[] files;

    public FileTest(List<MultipartFile>[] files) {
        this.files = files;
    }

    public FileTest() {}

    public List<MultipartFile>[] getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile>[] files) {
        this.files = files;
    }

//    public void addFileList(List<MultipartFile> fileList) {
//        this.files.add(fileList);
//    }
}
