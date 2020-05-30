package org.deb.account.exclusion.repository;

import org.deb.account.exclusion.entity.SubmittedRequest;
import org.deb.account.exclusion.enums.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "requests", path = "submittedRequest")
public interface UserRequestRepository extends JpaRepository<SubmittedRequest, UUID> {

  Page<SubmittedRequest> findByRequestStatus(@Param("requestStatus") RequestStatus requestStatus, Pageable pageable);
}
