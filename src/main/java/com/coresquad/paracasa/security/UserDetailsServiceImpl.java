package com.coresquad.paracasa.security;

import com.coresquad.paracasa.entity.User;
import com.coresquad.paracasa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Integer userId = userRepo.getUserByUsername(username);
        User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Usuario o Contraseña incorrectas"));

        return new MyUserDetails(user);
    }
}
