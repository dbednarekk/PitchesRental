package com.pitchrent.pitchesrental.managers;

import com.pitchrent.pitchesrental.entities.users.Account;
import com.pitchrent.pitchesrental.repositories.AccountRepository;
import com.pitchrent.pitchesrental.security.UserCredentialsExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static com.pitchrent.pitchesrental.common.I18n.NO_LOGIN_FOUND;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserCredentialsExtractor loadUserByUsername(String login) throws UsernameNotFoundException {
        try{
            Account acc = accountRepository.findAccountByLogin(login);
            return UserCredentialsExtractor.fromUserEntToCustomUserDetails(acc);
        }catch (NullPointerException e){
            throw new UsernameNotFoundException(NO_LOGIN_FOUND);
        }
    }
}
