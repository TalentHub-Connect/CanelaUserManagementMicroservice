package com.talenthub.CanelaUserManagementMicroservice.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private String role;
    private Long companyid;

}
