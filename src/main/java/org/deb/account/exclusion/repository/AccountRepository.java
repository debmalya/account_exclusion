package org.deb.account.exclusion.repository;

import org.deb.account.exclusion.entity.ExclusionAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<ExclusionAccounts,String> {
}
