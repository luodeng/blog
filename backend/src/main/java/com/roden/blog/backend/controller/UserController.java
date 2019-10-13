package com.roden.blog.backend.controller;

import com.roden.blog.backend.dao.UserRepository;
import com.roden.blog.backend.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/listAll")
    public Object listAll(){
        return userRepository.findAll();    }


    @PostMapping("/save")
    public void insert (UserDO userDO){
        //userDO.setGmtCreate(LocalDateTime.now());
        userDO.setGmtModified(userDO.getGmtCreate());
        userRepository.save(userDO);
    }


    @PostMapping("/delete")
    public void remove(Integer id){
        userRepository.deleteById(id);
    }

    @PostMapping("/update")
    public void update(UserDO userDO){
        userRepository.save(userDO);
    }

}
