package com.talenthub.CanelaUserManagementMicroservice.service;

import com.talenthub.CanelaUserManagementMicroservice.model.Rol;
import com.talenthub.CanelaUserManagementMicroservice.model.RolId;
import com.talenthub.CanelaUserManagementMicroservice.model.dto.UserWithRolesDTO;
import com.talenthub.CanelaUserManagementMicroservice.repository.RolRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public List<String> getRolesForUser(Integer id) {
        return rolRepository.getRolesForUser(id);
    }

    public boolean updateAllUsersRoles(List<UserWithRolesDTO> usuarios) {
        LocalDate  now =  LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(UserWithRolesDTO user: usuarios){
            for(String rol: user.getRoles()) {

                Rol role = rolRepository.findByIdCompuesto(rol, user.getId());
                System.out.println(role.toString());
                if(role==null){
                    Rol newRole = new Rol(
                            rol,
                            "",
                            1,
                            now.format(formatter),
                            user.getId(),
                            user.getId()
                    );
                    rolRepository.save(newRole);
                }
            }
        }
        List<Object[]> rolesExistentesCrudos = rolRepository.findAllOnlyKeys();
        List<RolId> rolesExistentes = objectListToRolId(rolesExistentesCrudos);
        List<RolId> nuevosRoles = usersWithRolesToRolId(usuarios);
        for(RolId rol: rolesExistentes){
            if(!nuevosRoles.contains(rol)){
                rolRepository.deleteFromRolId(rol.getName(),rol.getUserid());
            }
        }
        return true;
    }

    private List<RolId> objectListToRolId(List<Object[]> rolesExistentesCrudos) {
        List<RolId> roles =  new ArrayList<>();
        for(Object[] objects: rolesExistentesCrudos){
            RolId rol = new RolId(String.valueOf(objects[0]),Integer.parseInt(String.valueOf(objects[1])));
            roles.add(rol);
        }
        return roles;
    }

    private List<RolId> usersWithRolesToRolId(List<UserWithRolesDTO> usuarios) {
        List<RolId> roles = new ArrayList<>();
        for(UserWithRolesDTO user: usuarios){
            for(String rol: user.getRoles()){
                roles.add(new RolId(rol, user.getId()));
            }
        }
        return roles;
    }

    public Rol save(Rol rolAdmin) {
        return rolRepository.save(rolAdmin);
    }

}
