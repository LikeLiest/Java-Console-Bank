package org.bank.account;

import org.bank.exception.UserNotFoundException;
import org.bank.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class AccountService {
  private int idCounter;
  private final Map<Integer, Account> accountMap;
  
  private final int defaultAccountAmount;
  private final double transferCommission;
  
  public AccountService(int defaultAccountAmount, double transferCommission) {
    this.defaultAccountAmount = defaultAccountAmount;
    this.transferCommission = transferCommission;
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
    
    if (moneyToDeposit <= 100)
      throw new IllegalArgumentException("Нужно ввести депозит как минимум 100");
    
    account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);
  }
  
  public void withdrawFromAccount(int accountId, int amountToWithdraw) {
    Account account = findAccountById(accountId)
      .orElseThrow(() -> new UserNotFoundException(String.format("Аккаунт с ID: %d не найден ", accountId)));
    
    if (account.getMoneyAmount() < amountToWithdraw)
      throw new IllegalArgumentException("Нехватает денег на счету");
    
    account.setMoneyAmount(account.getMoneyAmount() - amountToWithdraw);
  }
  
  public Account closeAccount(int accountId) {
    Account accountToRemove = findAccountById(accountId)
      .orElseThrow(() -> new UserNotFoundException(String.format("Аккаунт с ID: %d не найден ", accountId)));
    List<Account> accountList = findAllAccounts(accountToRemove.getUserId());
    
    if (accountList.size() == 1)
      throw new IllegalArgumentException("Невозможно закрыть единственный аккаунт.");
    
    Account accountToDeposit = accountList.stream()
      .filter(it -> it.getId() != accountId)
      .findFirst()
      .orElse(null);
    
    assert accountToDeposit != null;
    accountToDeposit.setMoneyAmount(accountToDeposit.getMoneyAmount() + accountToRemove.getMoneyAmount());
    accountMap.remove(accountId);
    return accountToRemove;
  }
  
  public void transfer(int fromAccountId, int toAccountId, int amountToTransfer) {
    Account accountFromTransfer = findAccountById(fromAccountId)
      .orElseThrow(() -> new UserNotFoundException(String.format("Аккаунт с ID: %d не найден ", fromAccountId)));
    
    Account accountToTransfer = findAccountById(toAccountId)
      .orElseThrow(() -> new UserNotFoundException(String.format("Аккаунт с ID: %d не найден ", toAccountId)));
    
    if (accountFromTransfer.getMoneyAmount() <= 0)
      throw new IllegalArgumentException("Нехватает денег на счету для перевода");
    
    if (accountFromTransfer.getMoneyAmount() < amountToTransfer)
      throw new IllegalArgumentException("Нехватает денег для перевода");
    
    int totalAmountToDeposit = accountToTransfer.getId() != accountFromTransfer.getUserId()
      ? (int) (amountToTransfer - amountToTransfer * transferCommission)
      : amountToTransfer;
    
    accountFromTransfer.setMoneyAmount(accountFromTransfer.getMoneyAmount() - amountToTransfer);
    accountToTransfer.setMoneyAmount(accountToTransfer.getMoneyAmount() + totalAmountToDeposit);
  }
}
