package com.LinSY.backend.service.impl;

import com.LinSY.backend.mapper.UserMapper;
import com.LinSY.backend.pojo.User;
import com.LinSY.backend.pojo.UserExample;
import com.LinSY.backend.service.UserService;
import com.LinSY.backend.utils.QiniuUploadUtil;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Author LinSY
 * @Date 2018/10/4 22:51
 * @Version 1.0
 * @Description
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper ;

    @Override
    public Result login(long phone, String password) {

        UserExample example = new UserExample() ;
        example.createCriteria()
                .andPhoneEqualTo(phone)
                .andPasswordEqualTo(password)
                .andStatusEqualTo(2) ;

        List<User> users = userMapper.selectByExample(example) ;

        if (users.isEmpty()) {
            return Result.build(500, "no this user") ;
        }else {
            return Result.ok(users.get(0)) ;
        }

    }

    @Override
    public Result getInfo(Long userId) {
        return Result.ok(userMapper.selectByPrimaryKey(userId)) ;
    }

    @Override
    public Result updateInfo(User user) {

        try {
            userMapper.updateByPrimaryKeySelective(user) ;
        } catch (Exception e) {

            e.printStackTrace() ;
            return Result.build(500, "update error") ;
        }

        return Result.ok() ;
    }

    @Override
    public Result uploadPic(MultipartFile file) {
        String url = null ;
        try {
            // 调用七牛云上传工具
            url = QiniuUploadUtil.uploadFile(file) ;
        } catch (Exception e) {
            e.printStackTrace();
            return  Result.build(500, "upload error!") ;
        }
        return Result.ok(url);
    }
}
