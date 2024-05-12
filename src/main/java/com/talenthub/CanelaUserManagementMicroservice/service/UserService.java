package com.talenthub.CanelaUserManagementMicroservice.service;

import com.talenthub.CanelaUserManagementMicroservice.model.User;
import com.talenthub.CanelaUserManagementMicroservice.model.dto.UserDTO;
import com.talenthub.CanelaUserManagementMicroservice.repository.RolRepository;
import com.talenthub.CanelaUserManagementMicroservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolService rolService;

    public User save(UserDTO user) {
        User newUser = new User(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getUsername(),user.getPassword());
        newUser.setUsername(newUser.getFirstname()+"."+newUser.getLastname());
        boolean hayCorreo=false;
        Integer numeroCorreo = 1;
        User savedUser = null;
        while(!hayCorreo){
            try {
                savedUser = userRepository.save(newUser);
                hayCorreo = true;
            }catch (Exception ex){
                newUser.setUsername(newUser.getUsername()+numeroCorreo.toString());
                numeroCorreo++;
            }
        }
        if(rolService.isExistingRole(user.getRole())){
            rolService.addExistingRoleToUser(user.getRole(),savedUser.getId());
        }
        return savedUser;
    }
}
