package com.pitchrent.pitchesrental.security;

import com.pitchrent.pitchesrental.entities.users.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserCredentialsExtractor implements  UserDetails {
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserCredentialsExtractor fromUserEntToCustomUserDetails(Account account) {
        UserCredentialsExtractor cud = new UserCredentialsExtractor();
        cud.login = account.getLogin();
        cud.password = account.getPassword();
        cud.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(account.getAccessLevel().toString()));
        return cud;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
