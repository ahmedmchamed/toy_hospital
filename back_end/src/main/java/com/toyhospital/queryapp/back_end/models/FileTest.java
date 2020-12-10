package com.toyhospital.queryapp.back_end.models;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class FileTest {

    private List<List<MultipartFile>> files;

    public FileTest(List<List<MultipartFile>> files) {
        this.files = files;
    }

    public FileTest() {}

    public List<List<MultipartFile>> getFiles() {
        return files;
    }

    public void setFiles(List<List<MultipartFile>> files) {
        this.files = files;
    }
}
