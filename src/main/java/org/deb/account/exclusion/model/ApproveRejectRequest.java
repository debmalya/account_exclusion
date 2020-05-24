package org.deb.account.exclusion.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ApproveRejectRequest {
    private UUID requestID;
    private char action;
}
