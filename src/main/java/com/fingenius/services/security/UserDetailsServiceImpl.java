package com.fingenius.services.security;

import com.fingenius.domain.login.Authorities;
import com.fingenius.domain.login.Users;
import com.fingenius.services.queries.UsersQuery;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityManager entityManager = manager;
        Users user = UsersQuery.findUserByName(entityManager, username);
        Authorities authority = UsersQuery.findUserAuthority(entityManager, username);

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));

        return new User(user.getUsername(),user.getUserpassword(),user.isEnabled(),true,true,true,authorities);

    }

}
