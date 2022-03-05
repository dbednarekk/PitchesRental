package com.pitchrent.pitchesrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private AddressDTO address;
}
