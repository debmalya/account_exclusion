package org.deb.account.exclusion.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private final String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }


}
