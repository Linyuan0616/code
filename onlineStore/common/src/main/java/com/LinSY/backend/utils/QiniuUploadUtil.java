package com.LinSY.backend.utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName QiniuUploadUtil
 * @Author LinSY
 * @Date 2018/9/26 23:07
 * @Version 1.0
 * @Description
 */

public class QiniuUploadUtil {

    private static final String QINIU_ACCESS_KEY = "CBnZbzyi3aj2ExePYM5ZhyIrv-m55RXgym_0TrFT";

    private static final String QINIU_SECRET_KEY = "nWVKiNizXjYsvbjXzhA43Q-VzC2P4qo7njAL1PUf";

    private static final String QINIU_BUCKET = "onelinestore";

    /**
     * 当前为存储空间 onelinestore 的测试域名
     */
    private static final String QINIU_DOMAIN = "http://pfo5v9f5z.bkt.clouddn.com";

    private static Configuration cfg;

    private static UploadManager uploadManager;

    static {
        //zone2为华南地区
        cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
    }

    /**
     * 上传文件到七牛云存储服务器
     * @param file
     * @return 该文件的访问路径
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file) throws IOException {
            if (null == file || file.isEmpty()) {
            return null;
        }
        Auth auth = Auth.create(QINIU_ACCESS_KEY, QINIU_SECRET_KEY);
        String uploadToken = auth.uploadToken(QINIU_BUCKET);
        Response response = uploadManager.put(file.getInputStream(),generateFileName(), uploadToken, null, null);
        DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
        StringBuilder url = new StringBuilder(QINIU_DOMAIN);
        url.append("/");
        url.append(putRet.key);
        return url.toString();
    }



/*    *//**
     * 上传文件到七牛云存储服务器
     *  已过期的 qiniu-SDK
     * @param multipartFile
     * @return 该文件的访问路径
     *//*
    public static String uploadFile(MultipartFile multipartFile) throws AuthException, JSONException, IOException {
        // 将MultipartFile转化为File

        Config.ACCESS_KEY = QINIU_ACCESS_KEY ;
        Config.SECRET_KEY = QINIU_SECRET_KEY ;
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY) ;
        PutPolicy putPolicy = new PutPolicy(QINIU_BUCKET) ;
        String uptoken = null ;
        uptoken = putPolicy.token(mac);

        //将 multifile 转化为 File
        File file = null;
        file = transform(multipartFile);

        PutExtra extra = new PutExtra();
        PutRet ret = IoApi.putFile(uptoken, generateFileName(), file, extra);

        StringBuilder url = new StringBuilder(QINIU_DOMAIN);
        url.append("/");
        url.append(ret.getKey());
        return url.toString();

    }


    private static File transform(MultipartFile multipartFile) throws IOException{

        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        File excelFile = null ;

        excelFile = File.createTempFile(UUIDUtil.getUUID(), prefix);

        return excelFile ;
    }*/


    /**
     * 生成文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUIDUtil.getUUID();
    }

}