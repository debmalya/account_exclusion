package org.deb.account.exclusion.model;

import lombok.Data;

@Data
public class UserAuthenticationRequest {
    private String userName;
    private String password;
}
