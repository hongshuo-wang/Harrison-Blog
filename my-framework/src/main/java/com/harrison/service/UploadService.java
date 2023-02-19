package com.harrison.service;

import com.harrison.domain.result.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Harrison
 * @date: 2023/2/19 16:38
 * @Description: TODO
 */
public interface UploadService {
    ResponseResult uploadFile(MultipartFile img);
}
