package com.pitchrent.pitchesrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegisterDTO {
    private String login;
    private String password;
    private String email;
}
