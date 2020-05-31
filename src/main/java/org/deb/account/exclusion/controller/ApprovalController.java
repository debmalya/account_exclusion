package org.deb.account.exclusion.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/approval" + Constatnts.VERSION_ZERO)
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ApprovalController {
  private final UserRequestRepository userRequestRepository;

  private final AccountRepository accountRepository;

  @Value("${monthly.batch_file}")
  private String fileName;

  @Autowired
  public ApprovalController(UserRequestRepository userRepository, AccountRepository accountRepository) {
    this.userRequestRepository = userRepository;
    this.accountRepository = accountRepository;
  }


  @PostMapping(value = "/all")
  public ResponseEntity<?> excludeAccounts(@RequestBody List<ApproveRejectRequest> requestList) {
    String[] approver = new RequestUtil().getCurrentUserName();
        /*
        if (!approver[1].equals("Admin")){
            return new ResponseEntity<String>(String.format("Your role '%s' is not authorize to do this operation.",approver[1]),HttpStatus.UNAUTHORIZED );
        }

         */
    List<String> statusMessages = new ArrayList<>();
    for (ApproveRejectRequest eachApproveRejectRequest : requestList) {
      Optional<SubmittedRequest> pendingRequest = userRequestRepository.findById(eachApproveRejectRequest.getRequestID());
      String status = "";
      if (pendingRequest.isPresent()) {
        SubmittedRequest submittedRequest = pendingRequest.get();
        submittedRequest.setApprovedBy(approver[0]);
        submittedRequest.setApprovalDate(new Date());
        try {
          if (eachApproveRejectRequest.getAction() == 'A') {
            // add in exclusion_account
            accountRepository.save(new ExclusionAccounts(submittedRequest.getAccountNumber()));
            // need to remove from the file also
            submittedRequest.setRequestStatus(RequestStatus.APPROVED);
            status = String.format("Request with id '%s' got approved.", eachApproveRejectRequest.getRequestID());
          } else if (eachApproveRejectRequest.getAction() == 'R') {
            // need to remove from the file also
            submittedRequest.setRequestStatus(RequestStatus.REJECTED);
            status = String.format("Request with id '%s' got rejected.", eachApproveRejectRequest.getRequestID());
          } else if (eachApproveRejectRequest.getAction() == 'C') {
            // need to remove from the file also
            submittedRequest.setRequestStatus(RequestStatus.CANCELLED);
            status = String.format("Request with id '%s' got cancelled.", eachApproveRejectRequest.getRequestID());
          }
          userRequestRepository.save(submittedRequest);
        } catch (Exception exc) {
          statusMessages.add(String.format("Error occurred for account '%s'", submittedRequest.getAccountNumber(), exc.getMessage()));
        }
      } else {
        status = String.format("Request with id '%s' not found.", eachApproveRejectRequest.getRequestID());
        log.info(status);
      }
      statusMessages.add(status);
    }
    return new ResponseEntity<>(statusMessages, HttpStatus.OK);
  }

  @GetMapping(value = "/get/all")
  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(userRequestRepository.findAll(), HttpStatus.OK);
  }

  @PostMapping(value = "/runbatch")
  public ResponseEntity<?> batchRun() {
    // Read CSV file from classpath
//    Reader reader = null;
    log.info("1. batchRun called");
    StringBuilder status = new StringBuilder();
    CSVWriter csvWriter = null;
    try (Reader reader = Files.newBufferedReader(Paths.get(
      ClassLoader.getSystemResource(fileName).toURI()))) {
      log.info("2. creted reader");
      CSVReader csvReader = new CSVReader(reader);
      List<String[]> fileContent = csvReader.readAll();
      log.info(String.format("3. No. of accounts:  %d", fileContent.size()));

      // Get all excluded account

      List<Integer> accountsToBeRemoved = new ArrayList<>();
      for (int i = 1; i < fileContent.size(); i++) {
        log.info(String.format("Comparing with '%s'", fileContent.get(i)[0]));
        Optional<ExclusionAccounts> retrievedAccount = accountRepository.findById(fileContent.get(i)[0]);
        if (retrievedAccount.isPresent()) {
          log.info(String.format("Found matching account number '%s' in file.'", fileContent.get(i)[0]));
          accountsToBeRemoved.add(i);
//          break;
        }
      }

      for (int i = accountsToBeRemoved.size() - 1; i > -1; i--){
        String accountNumberToBeRemoved = fileContent.get(i)[0];
        log.info(String.format("Accounts to be removed '%s'",accountNumberToBeRemoved));
        String[] removedRecord = fileContent.remove(i);
        if (removedRecord!= null && removedRecord.length >0){
          log.info(String.format("Removed record '%s'",Arrays.toString(removedRecord)));
          status.append(String.format("Removed record '%s'",accountNumberToBeRemoved));
        }
      }


      csvWriter = new CSVWriter(new FileWriter(Paths.get(
        ClassLoader.getSystemResource(fileName).toURI()).toString()));
      csvWriter.writeAll(fileContent);
      log.info("4. File writing completed");
    } catch (URISyntaxException | IOException | CsvException e) {
      String errorMessage = String.format("error occurred while reading file '%s' error '%s'", fileName, e.getMessage());
      status.append(errorMessage);
      log.error(errorMessage, e);
    } finally {
      if (csvWriter != null) {
        try {
          csvWriter.flush();
          csvWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }

    return new ResponseEntity<>(status.toString(), HttpStatus.OK);
  }

}
