package com.talenthub.CanelaUserManagementMicroservice.repository;

import com.talenthub.CanelaUserManagementMicroservice.model.Rol;
import com.talenthub.CanelaUserManagementMicroservice.model.RolId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, String>, JpaSpecificationExecutor<Rol> {

    @Query(value= "SELECT * FROM rol r WHERE r.name LIKE :role", nativeQuery = true)
    List<Rol> findSimilarRole(@Param("role") String role);

    @Query(value= "SELECT r.name FROM rol r WHERE r.userid = :id", nativeQuery = true)
    List<String> getRolesForUser(@Param("id") Integer id);


    @Query(value= "SELECT * FROM rol r WHERE r.userid = :id AND r.name = :rol", nativeQuery = true)
    Rol findByIdCompuesto(@Param("rol") String rol, @Param("id") Integer id);

    @Query(value= "SELECT r.name,r.userid FROM rol r", nativeQuery = true)
    List<Object[]> findAllOnlyKeys();

    @Transactional
    @Modifying
    @Query(value= "DELETE FROM rol WHERE name = :name AND userid = :userid", nativeQuery = true)
    void deleteFromRolId(String name, int userid);
}