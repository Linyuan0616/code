package com.LinSY.backend.controller;

import com.LinSY.backend.pojo.User;
import com.LinSY.backend.service.UserService;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName UserController
 * @Author LinSY
 * @Date 2018/10/4 22:49
 * @Version 1.0
 * @Description  用户管理controller
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(Long phone, String password){
        return userService.login(phone, password) ;
    }

    @ResponseBody
    @RequestMapping("/getInfo")
    public Result getInfo(Long userId){
        return userService.getInfo(userId) ;
    }

    @ResponseBody
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public Result getInfo(User user){
        return userService.updateInfo(user) ;
    }

    @ResponseBody
    @RequestMapping(value = "/pic/upload",method = RequestMethod.POST)
    public Result uploadPin(@RequestParam("file") MultipartFile file){

       return userService.uploadPic(file) ;


    }

}
