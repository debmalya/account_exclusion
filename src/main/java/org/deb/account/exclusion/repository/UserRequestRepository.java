package org.deb.account.exclusion.repository;

import org.deb.account.exclusion.entity.SubmittedRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRequestRepository extends JpaRepository<SubmittedRequest, UUID> {

}
