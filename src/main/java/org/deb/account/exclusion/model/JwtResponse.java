package org.deb.account.exclusion.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {
  private final String jwtToken;
  private final String role;

  public String getJwtToken() {
    return jwtToken;
  }

  public JwtResponse(String jwtToken, String role) {
    this.jwtToken = jwtToken;
    this.role = role;
  }


}
