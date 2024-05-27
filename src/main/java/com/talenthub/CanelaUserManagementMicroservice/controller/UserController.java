package com.talenthub.CanelaUserManagementMicroservice.controller;

import com.talenthub.CanelaUserManagementMicroservice.model.User;
import com.talenthub.CanelaUserManagementMicroservice.model.dto.UserDTO;
import com.talenthub.CanelaUserManagementMicroservice.model.dto.UserWithRolesDTO;
import com.talenthub.CanelaUserManagementMicroservice.service.UserService;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @CrossOrigin
    @GetMapping("/find_all_with_roles")
    public List<UserWithRolesDTO> findAllWithRoles(){
        return userService.findAllWithRoles();
    }

    @CrossOrigin
    @GetMapping("/find_all_with_roles_by_company_id/{id}")
    public List<UserWithRolesDTO> findfindAllWithRolesByCompanyId(@PathVariable("id") Long id){
        return userService.findAllWithRolesByCompanyId(id);

    }

    @CrossOrigin
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    }
