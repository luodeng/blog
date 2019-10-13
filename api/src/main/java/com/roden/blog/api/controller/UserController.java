package com.roden.blog.api.controller;

import com.github.pagehelper.PageHelper;
import com.roden.blog.api.domain.UserDO;
import com.roden.blog.api.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;


@Log
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserInfo")
    UserDO getUserInfo(String userName){
        log.info("getUserInfo");
        return userService.getByUserName(userName);
    }

    @GetMapping("/listUser")
    List<UserDO> listUser(){
        PageHelper.startPage(1, 2);
        return userService.listAll();
    }

    @GetMapping("/countUser")
    int countUser(){
        return userService.countUser();
    }

    @RequestMapping("/saveUser")
    int saveUser(UserDO userDO){
        log.info("save method");
        log.info("userDO:"+userDO);
        userDO.setUserName("roden");
        userDO.setGmtCreate(LocalDateTime.now());
        userDO.setGmtModified(userDO.getGmtCreate());
        return  userService.saveUser(userDO);
    }
    @RequestMapping("/removeUser")
    int removeUser(Integer id){
        return  userService.removeUser(id);
    }
    @PostMapping("/updateUser")
    int updateUser(UserDO userDO){
        log.info("update method");
        UserDO updateUserDO=new UserDO();
        updateUserDO.setId(userDO.getId());
        updateUserDO.setUserName(userDO.getUserName());
        userDO.setGmtModified(LocalDateTime.now());
        return  userService.updateUser(userDO);
    }

}
