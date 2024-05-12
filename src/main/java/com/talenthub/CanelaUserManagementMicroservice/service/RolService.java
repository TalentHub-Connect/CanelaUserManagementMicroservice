package com.talenthub.CanelaUserManagementMicroservice.service;

import com.talenthub.CanelaUserManagementMicroservice.model.Rol;
import com.talenthub.CanelaUserManagementMicroservice.repository.RolRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;


    public boolean isExistingRole(String role) {
        List<Rol> roles = rolRepository.findSimilarRole(role);
        if(roles.isEmpty()){
            return false;
        }
        return true;
    }

    public void saveRoleFromUser(String role, Integer id) {
        
    }

    public void addExistingRoleToUser(String role, Integer id) {
        Rol rolAAsignar = rolRepository.findSimilarRole(role).get(0);
        LocalDate  now =  LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Rol newRole = new Rol(
                rolAAsignar.getName(),
                rolAAsignar.getDescription(),
                rolAAsignar.getStatus(),
                now.format(formatter),
                id,
                id
        );
        rolRepository.saveAndFlush(newRole);
    }
}
