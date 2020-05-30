package org.deb.account.exclusion.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExclusionAccountRequest {
  private List<String> accounts = new ArrayList<>();

}
