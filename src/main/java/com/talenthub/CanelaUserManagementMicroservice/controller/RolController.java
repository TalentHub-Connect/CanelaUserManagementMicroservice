package com.talenthub.CanelaUserManagementMicroservice.controller;

import com.talenthub.CanelaUserManagementMicroservice.model.dto.UserWithRolesDTO;
import com.talenthub.CanelaUserManagementMicroservice.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @CrossOrigin
    @PostMapping("/update_all_users_roles")
    public boolean saveAllWithRoles(@RequestBody List<UserWithRolesDTO> usuarios) {
        return rolService.updateAllUsersRoles(usuarios);
    }
}
