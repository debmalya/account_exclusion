package org.deb.account.exclusion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {
  private  String jwtToken;
  private  String role;

}
