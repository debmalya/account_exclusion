package org.deb.account.exclusion.controller;

import lombok.extern.slf4j.Slf4j;
import org.deb.account.exclusion.entity.ExclusionAccounts;
import org.deb.account.exclusion.entity.SubmittedRequest;
import org.deb.account.exclusion.enums.RequestStatus;
import org.deb.account.exclusion.model.ApproveRejectRequest;
import org.deb.account.exclusion.repository.AccountRepository;
import org.deb.account.exclusion.repository.UserRequestRepository;
import org.deb.account.exclusion.util.Constatnts;
import org.deb.account.exclusion.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/approval" + Constatnts.VERSION_ZERO)
@Slf4j
@CrossOrigin
public class ApprovalController {
    private final UserRequestRepository userRequestRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public ApprovalController(UserRequestRepository userRepository, AccountRepository accountRepository) {
        this.userRequestRepository = userRepository;
        this.accountRepository = accountRepository;
    }


    @PostMapping(value="/all")
    public ResponseEntity<?> excludeAccounts(@RequestBody List<ApproveRejectRequest> requestList) {
        String[] approver = new RequestUtil().getCurrentUserName();
        if (!approver[1].equals("Admin")){
            return new ResponseEntity<String>(String.format("Your role '%s' is not authorize to do this operation.",approver[1]),HttpStatus.UNAUTHORIZED );
        }
        List<String> statusMessages = new ArrayList<>();
        for (ApproveRejectRequest eachApproveRejectRequest : requestList) {
            Optional<SubmittedRequest> pendingRequest = userRequestRepository.findById(eachApproveRejectRequest.getRequestID());
            String status = "";
            if (pendingRequest.isPresent()) {
                SubmittedRequest submittedRequest = pendingRequest.get();
                submittedRequest.setApprovedBy(approver[0]);
                submittedRequest.setApprovalDate(new Date());
                try {
                    if (eachApproveRejectRequest.getAction()=='A'){
                        // add in exclusion_account
                        accountRepository.save(new ExclusionAccounts(submittedRequest.getAccountNumber()));
                        // need to remove from the file also
                        submittedRequest.setRequestStatus(RequestStatus.APPROVED);
                        status = String.format("Request with id '%s' got approved.", eachApproveRejectRequest.getRequestID());
                    }else if (eachApproveRejectRequest.getAction()=='R'){
                        // need to remove from the file also
                        submittedRequest.setRequestStatus(RequestStatus.REJECTED);
                        status = String.format("Request with id '%s' got rejected.", eachApproveRejectRequest.getRequestID());
                    }
                    userRequestRepository.save(submittedRequest);
                }catch(Exception exc){
                    statusMessages.add(String.format("Error occurred for account '%s'", submittedRequest.getAccountNumber(),exc.getMessage()));
                }
            } else {
                status = String.format("Request with id '%s' not found.", eachApproveRejectRequest.getRequestID());
                log.info(status);
            }
            statusMessages.add(status);
        }
        return new ResponseEntity<>(statusMessages, HttpStatus.OK);
    }

    @GetMapping(value="/get/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userRequestRepository.findAll(), HttpStatus.OK);
    }
}
