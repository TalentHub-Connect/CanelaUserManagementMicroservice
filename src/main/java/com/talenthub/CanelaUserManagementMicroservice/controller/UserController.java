package com.talenthub.CanelaUserManagementMicroservice.controller;

import com.talenthub.CanelaUserManagementMicroservice.model.User;
import com.talenthub.CanelaUserManagementMicroservice.model.dto.UserDTO;
import com.talenthub.CanelaUserManagementMicroservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/save")
    public User save(@RequestBody UserDTO user){
        return userService.save(user);
    }

    }
