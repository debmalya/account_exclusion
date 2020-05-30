package org.deb.account.exclusion.repository;

import org.deb.account.exclusion.entity.ExclusionAccounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(collectionResourceRel = "excludedAccounts", path = "accounts")
public interface AccountRepository extends JpaRepository<ExclusionAccounts,String> {
  Page<ExclusionAccounts> findByAccountNumberContaining(@RequestParam("accountNumber") String accountNumber, Pageable pageable);
}
