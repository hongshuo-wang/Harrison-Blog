package com.harrison.controller;

import com.harrison.annotation.SystemLog;
import com.harrison.domain.result.ResponseResult;
import com.harrison.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Harrison
 * @date: 2023/2/19 16:36
 * @Description: TODO
 */
@RestController
public class uploadController {
    @Autowired
    private UploadService uploadService;
    @PostMapping("/upload")
    @SystemLog(businessName = "用户上传文件")
    public ResponseResult uploadFile(MultipartFile img) {
        return uploadService.uploadFile(img);
    }
}
