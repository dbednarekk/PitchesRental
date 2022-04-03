package com.pitchrent.pitchesrental.controllers;

import com.pitchrent.pitchesrental.dto.AccountDTO;
import com.pitchrent.pitchesrental.dto.AuthDTO;
import com.pitchrent.pitchesrental.entities.users.Account;
import com.pitchrent.pitchesrental.exceptions.BaseException;
import com.pitchrent.pitchesrental.managers.AccountManager;
import com.pitchrent.pitchesrental.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pitchrent.pitchesrental.common.I18n.INVALID_CREDENTIALS;

@CrossOrigin
@RestController
@RequestMapping("/api/Auth")
public class AuthController {

    private final AccountManager accountManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(AccountManager accountManager, JwtProvider jwtProvider) {
        this.accountManager = accountManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO authDTO) throws BaseException {
        try{
            accountManager.getAccountByLogin(authDTO.getLogin());
            String token = jwtProvider.generateToken(authDTO.getLogin());
            return ResponseEntity.accepted().body(token);
        }catch (Exception e ){
            throw new BaseException(INVALID_CREDENTIALS);
        }

    }
}
