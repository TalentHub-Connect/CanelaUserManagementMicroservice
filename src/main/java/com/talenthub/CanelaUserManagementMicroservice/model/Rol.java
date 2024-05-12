package com.talenthub.CanelaUserManagementMicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RolId.class)
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "creationdate")
    private String creationdate;

    @Column(name = "foreignuserid", nullable = false)
    private Integer foreignuserid;

    @Id
    @Column(name = "userid", nullable = false)
    private Integer userid;

}

