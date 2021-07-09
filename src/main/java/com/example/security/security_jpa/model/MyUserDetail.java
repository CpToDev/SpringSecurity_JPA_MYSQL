package com.example.security.security_jpa.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MyUserDetail implements UserDetails {

    private String username;
    private String password;
    private String authorities;

    public MyUserDetail(Person person) {

        this.username=person.getUsername();
        this.password=person.getPassword();
        this.authorities=person.getAuthorities();
    }
    public MyUserDetail(){

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Arrays.stream(authorities.split(","))
               .map(s-> new SimpleGrantedAuthority(s)).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return true;
    }
}
