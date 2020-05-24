package org.deb.account.exclusion.enums;

public enum RequestStatus {

    APPROVED("approved"),
    REJECTED("rejected"),
    PENDING("pending"),
    CANCELLED("cancelled");


    private String requestStatus;
    RequestStatus(String status){
        this.requestStatus = status;
    }

    public String getRequestStatus(){
        return requestStatus;
    }
}
