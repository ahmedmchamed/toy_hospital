package com.toyhospital.queryapp.back_end.models;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class FileTest {

    private MultipartFile[][] files;

    public FileTest(MultipartFile[][] files) {
        this.files = files;
    }

    public FileTest() {}

    public MultipartFile[][] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[][] files) {
        this.files = files;
    }

//    public void addFileList(List<MultipartFile> fileList) {
//        this.files.add(fileList);
//    }
}
