package com.LinSY.backend.service;

import com.LinSY.backend.pojo.User;
import com.LinSY.backend.utils.pojo.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName UserService
 * @Author LinSY
 * @Date 2018/10/4 22:50
 * @Version 1.0
 * @Description
 */

public interface UserService {

    /**
     * 登陆
     * @param phone
     * @param password
     * @return
     */
    Result login(long phone, String password) ;

    Result getInfo(Long userId) ;

    Result updateInfo(User user) ;

    Result uploadPic(MultipartFile file) ;

}
