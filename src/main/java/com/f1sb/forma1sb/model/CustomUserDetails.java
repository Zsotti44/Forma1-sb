package com.f1sb.forma1sb.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Felhasználók
 */
public class CustomUserDetails implements UserDetails {
    private int userid;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(int userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userid = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public int getUserid() {
        return userid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFirstAuthority() {
        return authorities.stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null); // Visszaadja az első authority-t, ha van
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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
