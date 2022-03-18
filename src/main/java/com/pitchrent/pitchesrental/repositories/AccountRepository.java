package com.pitchrent.pitchesrental.repositories;

import com.pitchrent.pitchesrental.entities.users.AccessLevel;
import com.pitchrent.pitchesrental.entities.users.Account;
import com.pitchrent.pitchesrental.entities.users.Customer;
import com.pitchrent.pitchesrental.validators.Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account,Long> {
    @Query("select account from Customer ")
    List<Account> getAllCustomers();

    @Query("select account from Manager ")
    List<Account> getAllManagers();

    @Query("select account from Administrator ")
    List<Account> getAllAdmins();

    Account findAccountByLogin(@Login String login);

    @Query("select acc.account from Customer acc where acc.account.login = ?1")
    Account findCustomerByLogin(@Login String login);

    @Query("select acc.account from Manager acc where acc.account.login = ?1")
    Account findManagerByLogin(@Login String login);

    @Query("select acc.account from Administrator acc where acc.account.login = ?1")
    Account findAdminByLogin(@Login String login);

}
