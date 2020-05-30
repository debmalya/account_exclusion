package org.deb.account.exclusion.entity;

import lombok.Data;
import org.deb.account.exclusion.enums.RequestStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "USER_REQUESTS")

public class SubmittedRequest {
    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID requestID;
    private String submittedBy;
    private Date submittedDate;
    private String approvedBy;
    private Date approvalDate;
    private RequestStatus requestStatus;
//    private ActionOnAccount actionOnAccount;
    @Column(unique=true)
    private String accountNumber;
}
