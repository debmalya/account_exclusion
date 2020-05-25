package org.deb.account.exclusion.model;

import lombok.Data;
import org.deb.account.exclusion.enums.ActionOnAccount;

@Data
public class ExclusionAccountRequest {
  private String accountNumber;
  private ActionOnAccount actionOnAccount;
}
