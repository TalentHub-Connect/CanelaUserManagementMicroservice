package com.talenthub.CanelaUserManagementMicroservice.repository;

import com.talenthub.CanelaUserManagementMicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

    List<User> findAllByCompanyid(Long id);
}