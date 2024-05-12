package com.talenthub.CanelaUserManagementMicroservice.controller;

import com.talenthub.CanelaUserManagementMicroservice.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    }
