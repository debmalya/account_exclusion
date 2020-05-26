package org.deb.account.exclusion.repository;

import org.deb.account.exclusion.entity.ExclusionAccounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface AccountRepository extends JpaRepository<ExclusionAccounts,String> {
  Page<ExclusionAccounts> findByAccountNumberContaining(@RequestParam("accountNumber") String accountNumber, Pageable pageable);
}
