package com.pitchrent.pitchesrental.controllers;

import com.pitchrent.pitchesrental.common.EtagIntegrityVerifier;
import com.pitchrent.pitchesrental.dto.AccountDTO;
import com.pitchrent.pitchesrental.dto.AccountRegisterDTO;
import com.pitchrent.pitchesrental.dto.CustomerDTO;
import com.pitchrent.pitchesrental.dto.CustomerRegisterDTO;
import com.pitchrent.pitchesrental.dto.changedata.ChangeAccountDTO;
import com.pitchrent.pitchesrental.dto.changedata.ChangeCustomerDTO;
import com.pitchrent.pitchesrental.exceptions.BaseException;
import com.pitchrent.pitchesrental.managers.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Account")
public class AccountController {

    @Autowired
    AccountManager accountManager;

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAccounts() {
        return accountManager.getAll();
    }

    @GetMapping(path = "/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers() {
        return accountManager.getAllCustomers();
    }

    @GetMapping(path = "/managers")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllManagers() {
        return accountManager.getAllManagers();
    }

    @GetMapping(path = "/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAllAdmins() {
        return accountManager.getAllAdmins();
    }

    @GetMapping(path = "/login/{login}")
    public ResponseEntity<AccountDTO> getAccountByLogin(@PathVariable String login) throws BaseException {
        return ResponseEntity.ok()
                .eTag(EtagIntegrityVerifier.calculateEntitySignature(accountManager.getAccountByLogin(login)))
                .body(accountManager.getAccountByLogin(login));
    }

    @GetMapping(path = "/customer/{login}")
    public ResponseEntity<CustomerDTO> getCustomerByLogin(@PathVariable String login) throws BaseException {
        return ResponseEntity.ok()
                .eTag(EtagIntegrityVerifier.calculateEntitySignature(accountManager.getCustomerByLogin(login)))
                .body(accountManager.getCustomerByLogin(login));
    }

    @GetMapping(path = "/manager/{login}")
    public ResponseEntity<AccountDTO> getManagerByLogin(@PathVariable String login) throws BaseException {
        return ResponseEntity.ok()
                .eTag(EtagIntegrityVerifier.calculateEntitySignature(accountManager.getAccountByLogin(login)))
                .body(accountManager.getAccountByLogin(login));
    }

    @GetMapping(path = "/admin/{login}")
    public ResponseEntity<AccountDTO> getAdminByLogin(@PathVariable String login) throws BaseException {
        return ResponseEntity.ok()
                .eTag(EtagIntegrityVerifier.calculateEntitySignature(accountManager.getAccountByLogin(login)))
                .body(accountManager.getAccountByLogin(login));
    }


    @PostMapping(path = "/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CustomerRegisterDTO customer) throws BaseException {
        accountManager.createCustomer(customer);
    }

    @PostMapping(path = "/addManager")
    @ResponseStatus(HttpStatus.CREATED)
    public void createManager(@RequestBody AccountRegisterDTO account) throws BaseException {
        accountManager.createManager(account);
    }

    @PostMapping(path = "/addAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAdmin(@RequestBody AccountRegisterDTO account) throws BaseException {
        accountManager.createAdmin(account);
    }

    @PutMapping(path = "update/customer")
    public ResponseEntity<String> updateCustomer(@RequestBody ChangeCustomerDTO dto, @RequestHeader("If-Match") String Etag) throws BaseException {
        if (!EtagIntegrityVerifier.verifyEntityIntegrity(dto, Etag)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Invalid Etag");
        }
        accountManager.updateCustomer(dto);
        return ResponseEntity.ok().body("");
    }

    @PutMapping(path = "update/manager")
    public ResponseEntity<String> updateManager(@RequestBody ChangeAccountDTO dto, @RequestHeader("If-Match") String Etag) throws BaseException {
        if (!EtagIntegrityVerifier.verifyEntityIntegrity(dto, Etag)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Invalid Etag");
        }
        accountManager.updateManager(dto);
        return ResponseEntity.ok().body("");
    }

    @PutMapping(path = "update/admin")
    public ResponseEntity<String> updateAdmin(@RequestBody ChangeAccountDTO dto, @RequestHeader("If-Match") String Etag) throws BaseException {
        if (!EtagIntegrityVerifier.verifyEntityIntegrity(dto, Etag)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Invalid Etag");
        }
        accountManager.updateAdmin(dto);
        return ResponseEntity.ok().body("");
    }

    @PutMapping(path = "block/{login}")
    public void blockAccount(@PathVariable String login) throws BaseException {
        accountManager.blockAccount(login);
    }

}
