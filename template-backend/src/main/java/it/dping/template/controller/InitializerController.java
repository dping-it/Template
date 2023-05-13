package it.dping.template.controller;

import it.dping.template.model.auth.Role;
import it.dping.template.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitializerController {

    @Autowired
    RoleRepository roleRepository;
    /// INIT ROLE TABLE
    @PostConstruct
    public void init(){
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()) {
            List<String> typeRole = List.of(new String[]{"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER", "ROLE_STANDARD", "ROLE_PROFESSIONAL", "ROLE_ELITE"});
            typeRole.forEach(x -> {
                Role role = new Role();
                role.setName(x);
                role = roleRepository.save(role);
                System.out.println(role.getName());
            });
        }
    }
}
