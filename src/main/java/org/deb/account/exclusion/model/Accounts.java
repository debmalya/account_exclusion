package org.deb.account.exclusion.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.deb.account.exclusion.entity.ExclusionAccounts;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Accounts {
  private List<ExclusionAccounts> accountsList = new ArrayList<>();
}
