package org.deb.account.exclusion.controller;

import lombok.extern.slf4j.Slf4j;
import org.deb.account.exclusion.entity.ExclusionAccounts;
import org.deb.account.exclusion.entity.SubmittedRequest;
import org.deb.account.exclusion.enums.RequestStatus;
import org.deb.account.exclusion.repository.AccountRepository;
import org.deb.account.exclusion.repository.UserRequestRepository;
import org.deb.account.exclusion.util.Constatnts;
import org.deb.account.exclusion.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account" + Constatnts.VERSION_ZERO)
@Slf4j
@CrossOrigin
public class AccountController {
    private final AccountRepository accountRepository;

    private final UserRequestRepository userRequestRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository, UserRequestRepository userRequestRepository) {
        this.accountRepository = accountRepository;
        this.userRequestRepository = userRequestRepository;
    }


    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    public ResponseEntity<List<ExclusionAccounts>> getAllExcludedAccounts() {
        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/exclude", method = RequestMethod.POST)
    public ResponseEntity<?> excludeAccounts(@RequestBody List<ExclusionAccounts> accountList) {
        String[] submitter = new RequestUtil().getCurrentUserName();
        if (!submitter[1].equals("User")){
            return new ResponseEntity<String>(String.format("Your role '%s' is not authorize to do this operation.",submitter[1]),HttpStatus.UNAUTHORIZED );
        }
        List<SubmittedRequest> submittedRequests = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        for (ExclusionAccounts savedAccount: accountList){
            try {
                SubmittedRequest submittedRequest = new SubmittedRequest();
                submittedRequest.setRequestID(UUID.randomUUID());
                submittedRequest.setAccountNumber(savedAccount.getAccountNumber());
                submittedRequest.setSubmittedBy(submitter[0]);
                submittedRequest.setSubmittedDate(new Date());
                submittedRequest.setRequestStatus(RequestStatus.PENDING);
                SubmittedRequest savedSubmittedRequest = userRequestRepository.saveAndFlush(submittedRequest);
                log.info(savedSubmittedRequest.getRequestID().toString());
                submittedRequests.add(savedSubmittedRequest);
            }catch(Exception exc){
                errorMessages.add(String.format("There is already a request for account '%s'. ",savedAccount.getAccountNumber()));
            }
        }
        if (errorMessages.isEmpty()) {
            return new ResponseEntity<>(submittedRequests, HttpStatus.CREATED);
        } else{
            if (!submittedRequests.isEmpty()){
                errorMessages.add(submittedRequests.toString());
            }
            return new ResponseEntity<>(errorMessages,HttpStatus.EXPECTATION_FAILED);
        }
    }


}
