package com.harrison.service.impl;

import com.google.gson.Gson;
import com.harrison.domain.result.ResponseResult;
import com.harrison.enums.AppHttpCodeEnum;
import com.harrison.exception.SystemException;
import com.harrison.service.UploadService;
import com.harrison.utils.PathUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author: Harrison
 * @date: 2023/2/19 16:38
 * @Description: TODO
 */
@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class OssUploadServiceImpl implements UploadService {

    private String secretKey;
    private String accessKey;
    private String bucket;
    private String realmName;
    @Override
    public ResponseResult uploadFile(MultipartFile img) {
        // 判断文件类型和文件大小
        String fileName = img.getOriginalFilename();
        if(!fileName.endsWith(".png") && !fileName.endsWith(".jgp")) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        // 如果判断通过，上传到oss,返回外链地址
        String filePath = PathUtils.generateFilePath(fileName);
        String url = uploadOss(img, filePath);
        return ResponseResult.okResult(url);
    }

    private String uploadOss(MultipartFile file, String filePath) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        // 指定分片上传版本
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;

        UploadManager uploadManager = new UploadManager(cfg);


        //默认不指定key的情况下，以文件内容的hash值作为文件名

        String key = filePath;

        try {
            // 文件上传
            InputStream byteInputStream = file.getInputStream();

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return realmName + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return null;
    }
}
