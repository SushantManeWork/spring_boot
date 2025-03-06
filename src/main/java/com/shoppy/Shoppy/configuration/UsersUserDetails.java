package com.shoppy.Shoppy.configuration;

import com.shoppy.Shoppy.entity.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UsersUserDetails implements UserDetails {

    private Users _users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority>  grantedAuthorities=
                new ArrayList<>();
        _users.getRoles().forEach(r-> grantedAuthorities.add(new SimpleGrantedAuthority(r.getRole())));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return _users.getPassword();
    }

    @Override
    public String getUsername() {
        return _users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return _users.getDeletedBy()==null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return _users.getDeletedBy()==null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return _users.getDeletedBy()==null;
    }

    @Override
    public boolean isEnabled() {
        return _users.getDeletedBy()==null;
    }
}
