package com.pangsoramdepo.rbac.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.pangsoramdepo.rbac.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {

    private String username;

    private String password;

    private boolean active;
    
    private List<GrantedAuthority> authorities;

    public UserDetail(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = user.getRoles().stream()
                    .map(obj -> new SimpleGrantedAuthority("ROLE_" + obj.getName()))
                    .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
    
}
