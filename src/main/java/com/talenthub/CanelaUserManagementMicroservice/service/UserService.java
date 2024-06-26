package com.talenthub.CanelaUserManagementMicroservice.service;

import com.talenthub.CanelaUserManagementMicroservice.model.Rol;
import com.talenthub.CanelaUserManagementMicroservice.model.User;
import com.talenthub.CanelaUserManagementMicroservice.model.dto.UserDTO;
import com.talenthub.CanelaUserManagementMicroservice.model.dto.UserWithRolesDTO;
import com.talenthub.CanelaUserManagementMicroservice.repository.RolRepository;
import com.talenthub.CanelaUserManagementMicroservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolService rolService;

    public User save(UserDTO user) {
        User newUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getCompanyid());
        newUser.setUsername(newUser.getFirstname() + "." + newUser.getLastname());
        boolean hayCorreo = false;
        Integer numeroCorreo = 1;
        User savedUser = null;
        String ogUsername = newUser.getUsername();
        while (!hayCorreo) {
            try {
                savedUser = userRepository.save(newUser);
                hayCorreo = true;
            } catch (Exception ex) {
                newUser.setUsername(ogUsername + numeroCorreo.toString());
                numeroCorreo++;
            }
        }
        System.out.println(user.toString());
        if (rolService.isExistingRole(user.getRole())) {
            rolService.addExistingRoleToUser(user.getRole(), savedUser.getId());
        }else{
            if(user.getRole().equals("ADMIN")){
                LocalDate now =  LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Rol rolAdmin = new Rol(
                        user.getRole(),
                        "Administrador de su empresa",
                        1,
                        now.format(formatter),
                        savedUser.getId(),
                        savedUser.getId()
                );
                rolService.save(rolAdmin);
            }else{
                LocalDate now =  LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Rol rolNOAdmin = new Rol(
                        user.getRole(),
                        user.getRole(),
                        1,
                        now.format(formatter),
                        savedUser.getId(),
                        savedUser.getId()
                );
                rolService.save(rolNOAdmin);
            }
        }
        return savedUser;
    }

    public List<UserWithRolesDTO> findAllWithRoles() {
        List<User> usuarios = userRepository.findAll();
        List<UserWithRolesDTO> usuariosConRoles = new ArrayList<>();
        for (User usuario : usuarios) {
            List<String> roles = rolService.getRolesForUser(usuario.getId());
            UserWithRolesDTO nuevoUsuario = new UserWithRolesDTO(
                    usuario.getId(),
                    usuario.getFirstname(),
                    usuario.getLastname(),
                    usuario.getEmail(),
                    usuario.getUsername(),
                    usuario.getPassword(),
                    roles
            );
            usuariosConRoles.add(nuevoUsuario);
        }
        return usuariosConRoles;
    }


    public List<UserWithRolesDTO> findAllWithRolesByCompanyId(Long id) {
        List<User> usuarios = userRepository.findAllByCompanyid(id);
        List<UserWithRolesDTO> usuariosConRoles = new ArrayList<>();
        for (User usuario : usuarios) {
            List<String> roles = rolService.getRolesForUser(usuario.getId());
            UserWithRolesDTO nuevoUsuario = new UserWithRolesDTO(
                    usuario.getId(),
                    usuario.getFirstname(),
                    usuario.getLastname(),
                    usuario.getEmail(),
                    usuario.getUsername(),
                    usuario.getPassword(),
                    roles
            );
            usuariosConRoles.add(nuevoUsuario);
        }
        return usuariosConRoles;
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        BeanUtils.copyProperties(userDTO, existingUser, "id", "username"); // Excluir 'id' y 'username' de la actualización
        return userRepository.save(existingUser);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}
