package org.deb.account.exclusion.entity;

import lombok.Data;
import org.deb.account.exclusion.enums.ActionOnAccount;
import org.deb.account.exclusion.enums.RequestStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "USER_REQUESTS")

public class SubmittedRequest {
    @Id
    private UUID requestID;
    private String submittedBy;
    private Date submittedDate;
    private String approvedBy;
    private Date approvalDate;
    private RequestStatus requestStatus;
    private ActionOnAccount actionOnAccount;
    @Column(unique=true)
    private String accountNumber;
}
