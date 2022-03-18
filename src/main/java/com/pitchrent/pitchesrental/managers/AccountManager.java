package com.pitchrent.pitchesrental.managers;

import com.pitchrent.pitchesrental.dto.AccountDTO;
import com.pitchrent.pitchesrental.dto.AccountRegisterDTO;
import com.pitchrent.pitchesrental.dto.CustomerDTO;
import com.pitchrent.pitchesrental.dto.CustomerRegisterDTO;
import com.pitchrent.pitchesrental.dto.changedata.ChangeAccountDTO;
import com.pitchrent.pitchesrental.dto.changedata.ChangeCustomerDTO;
import com.pitchrent.pitchesrental.entities.users.Account;
import com.pitchrent.pitchesrental.entities.users.Administrator;
import com.pitchrent.pitchesrental.entities.users.Customer;
import com.pitchrent.pitchesrental.entities.users.Manager;
import com.pitchrent.pitchesrental.exceptions.AccountManagerException;
import com.pitchrent.pitchesrental.exceptions.BaseException;
import com.pitchrent.pitchesrental.managers.mappers.AccountMapper;
import com.pitchrent.pitchesrental.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

import static com.pitchrent.pitchesrental.common.I18n.*;

@ManagedBean
public class AccountManager {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AccountDTO> getAll(){
        List<AccountDTO> res = new ArrayList<>();
        for (Account ac : accountRepository.findAll()
             ) {
            res.add(AccountMapper.toAccountDTO(ac));
        }
       return res;
    }
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> res = new ArrayList<>();
        for (Account ac : accountRepository.getAllCustomers()
        ) {
            Customer customer = (Customer) ac.getAccessLevel();
            res.add(AccountMapper.toCustomerDTO(ac, customer));
        }
        return res;
    }
    public List<AccountDTO> getAllManagers(){
        List<AccountDTO> res = new ArrayList<>();
        for (Account ac : accountRepository.getAllManagers()
        ) {
            res.add(AccountMapper.toAccountDTO(ac));
        }
        return res;
    }
    public List<AccountDTO> getAllAdmins(){
        List<AccountDTO> res = new ArrayList<>();
        for (Account ac : accountRepository.getAllAdmins()
        ) {
            res.add(AccountMapper.toAccountDTO(ac));
        }
        return res;
    }
    public AccountDTO getAccountByLogin( String login) throws BaseException {
        try {
            return AccountMapper.toAccountDTO(accountRepository.findAccountByLogin(login));
        }catch (NullPointerException e){
            throw new AccountManagerException(NO_LOGIN_FOUND);
        }
    }
    public CustomerDTO getCustomerByLogin( String login) throws BaseException {
        try {
            Account ac = accountRepository.findCustomerByLogin(login);
            return AccountMapper.toCustomerDTO(ac, (Customer) ac.getAccessLevel());
        }catch (NullPointerException e){
            throw new AccountManagerException(NO_LOGIN_FOUND);
        }

    }


    public void createCustomer(CustomerRegisterDTO dto) throws BaseException {
        for (Account acc: accountRepository.findAll()
             ) {
            if( acc.getLogin().equals(dto.getLogin())) throw new AccountManagerException(LOGIN_TAKEN);
        }
        Customer customer = AccountMapper.toCustomerWithPassword(dto);
        Account account = AccountMapper.toAccountWithPassword(dto,passwordEncoder.encode(dto.getPassword()));
        account.setAccessLevel(customer);
        customer.setAccount(account);
        accountRepository.save(account);
    }

    public void createManager(AccountRegisterDTO account)throws BaseException{
        for (Account acc: accountRepository.findAll()
        ) {
            if( acc.getLogin().equals(account.getLogin())) throw new AccountManagerException(LOGIN_TAKEN);
        }
        Manager manager = new Manager();
        Account res = AccountMapper.toAccountWithPassword(account,passwordEncoder.encode(account.getPassword()));
        res.setAccessLevel(manager);
        manager.setAccount(res);
        accountRepository.save(res);
    }
    public void createAdmin(AccountRegisterDTO account)throws BaseException{
        for (Account acc: accountRepository.findAll()
        ) {
            if( acc.getLogin().equals(account.getLogin())) throw new AccountManagerException(LOGIN_TAKEN);
        }
        Administrator admin = new Administrator();
        Account res = AccountMapper.toAccountWithPassword(account,passwordEncoder.encode(account.getPassword()));
        res.setAccessLevel(admin);
        admin.setAccount(res);
        accountRepository.save(res);
    }
    public void updateCustomer(ChangeCustomerDTO dto) throws BaseException {
       Account account = accountRepository.findCustomerByLogin(dto.getLogin());
       Customer customer = (Customer) account.getAccessLevel();
        checkForOptimisticLock(dto.getVersion(), account.getVersion());
        account.setEmail(dto.getEmail());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.getAddress().setStreet(dto.getAddress().getStreet());
        customer.getAddress().setCity(dto.getAddress().getCity());
        customer.getAddress().setCountry(dto.getAddress().getCountry());
        customer.getAddress().setPostCode(dto.getAddress().getPostCode());
        accountRepository.save(account);

    }
    public void updateManager(ChangeAccountDTO dto) throws BaseException {
        Account account = accountRepository.findAccountByLogin(dto.getLogin());
        checkForOptimisticLock(dto.getVersion(), account.getVersion());
        account.setEmail(dto.getEmail());
        accountRepository.save(account);
    }
    public void updateAdmin(ChangeAccountDTO dto) throws BaseException {
        Account account = accountRepository.findAccountByLogin(dto.getLogin());
        checkForOptimisticLock(dto.getVersion(), account.getVersion());
        account.setEmail(dto.getEmail());
        accountRepository.save(account);

    }
    public void blockAccount(String login) throws BaseException {
        Account account = accountRepository.findAccountByLogin(login);
        account.setActive(!account.getActive());
        accountRepository.save(account);
    }


    public static void checkForOptimisticLock(Long v1, Long v2) throws BaseException {
        if(!v1.equals(v2)) throw new AccountManagerException(OPTIMISTIC_LOCK_EXCEPTION);
    }
}
