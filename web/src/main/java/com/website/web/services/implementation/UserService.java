package com.website.web.services.implementation;

import com.website.web.enums.Role;
import com.website.web.models.Users;
import com.website.web.repositories.UsersRepository;
import com.website.web.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Users> findAll() {
        List<Users> users = usersRepository.findAll();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getRoles().equals(roles));
            {
                return users;
            }
        }
        return null;
    }

    @Override
    public void banUser(Long id) {
        Users user = usersRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }
        }
        usersRepository.save(user);
    }

    @Override
    public boolean save(Users users) {
        if (usersRepository.findByEmail(users.getEmail()) != null) return false;
        users.setActive(true);
        users.getRoles().add(Role.USER);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Users getUserByPrincipal(Principal principal) {
        if (principal == null) return new Users();
        return usersRepository.findByEmail(principal.getName());
    }
}
