package com.pitchrent.pitchesrental.dto.changedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pitchrent.pitchesrental.common.SignableEntity;
import com.pitchrent.pitchesrental.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAccountDTO implements SignableEntity {

        private String login;
        private String email;
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
