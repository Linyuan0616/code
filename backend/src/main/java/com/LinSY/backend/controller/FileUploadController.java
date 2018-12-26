package com.LinSY.backend.controller;

import com.LinSY.backend.service.ItemService;
import com.LinSY.backend.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName FileUploadController
 * @Author LinSY
 * @Date 2018/9/26 23:27
 * @Version 1.0
 * @Description 七牛云上传图片controller
 */
@Controller
public class FileUploadController {

    @Autowired
    private ItemService itemService ;

    /**
     * 上传文件
     * @param uploadFile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pic/upload",method = RequestMethod.POST)
    public String uploadPin(MultipartFile uploadFile){

        Map resultMap = itemService.fileUpload(uploadFile) ;

        return JSONUtil.objectToJson(resultMap);
    }


}
