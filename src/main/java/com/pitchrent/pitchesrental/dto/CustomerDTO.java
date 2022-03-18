package com.pitchrent.pitchesrental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pitchrent.pitchesrental.common.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements SignableEntity {
    private Long id;
    private String login;
    private String email;
    private Boolean active;
    private String firstName;
    private String lastName;
    private AddressDTO address;
    private Long version;
    @JsonIgnore
    @Override
    public String getSignablePayload() {
        return login + "." + version;
    }
}
