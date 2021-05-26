package com.teletabist.clubby.user;

import java.util.Collection;

import com.teletabist.clubby.user.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecureUserPrincipal implements UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = 2521436728555064716L;
    private User user;

    public SecureUserPrincipal(User u) {
        this.user = u;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getRoles();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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
        return this.user.isVerified();
    }

    public User getUser() {
        return this.user;
    }
}