package com.talenthub.CanelaUserManagementMicroservice.repository;

import com.talenthub.CanelaUserManagementMicroservice.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, String>, JpaSpecificationExecutor<Rol> {

    @Query(value= "SELECT * FROM rol r WHERE r.name LIKE :role", nativeQuery = true)
    List<Rol> findSimilarRole(@Param("role") String role);
}