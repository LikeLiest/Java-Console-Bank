package org.bank.account;

import org.bank.exception.UserNotFoundException;
import org.bank.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class AccountService {
  private int idCounter;
  private final Map<Integer, Account> accountMap;
  
  public AccountService() {
    this.idCounter = 0;
    this.accountMap = new HashMap<>();
  }
  
  public Account createAccount(User user) {
    idCounter++;
    Account account = new Account(idCounter, user.getId(), 0);
    accountMap.put(account.getId(), account);
    return account;
  }
  
  public Optional<Account> findAccountById(int id) {
    return Optional.ofNullable(accountMap.get(id));
  }
  
  public List<Account> findAllAccounts(int userId) {
    return accountMap.values()
      .stream()
      .filter(account -> account.getUserId() == userId)
      .collect(Collectors.toUnmodifiableList());
  }
  
  public void depositAccount(int accountId, int moneyToDeposit) {
    Account account = findAccountById(accountId)
      .orElseThrow(() -> new UserNotFoundException(String.format("Аккаунт с ID: %d не найден ", accountId)));
    
    if(moneyToDeposit <= 100)
      throw new IllegalArgumentException("Нужно ввести депозит как минимум 100");
    
    account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);
  }
}
