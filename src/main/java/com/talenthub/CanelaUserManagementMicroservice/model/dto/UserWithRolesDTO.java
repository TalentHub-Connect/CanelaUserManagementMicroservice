package com.talenthub.CanelaUserManagementMicroservice.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserWithRolesDTO {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String username;

    private String password;

    private List<String> roles;
}
