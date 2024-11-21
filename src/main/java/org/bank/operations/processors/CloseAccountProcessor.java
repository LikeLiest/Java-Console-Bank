package org.bank.operations.processors;

import lombok.RequiredArgsConstructor;
import org.bank.account.Account;
import org.bank.account.AccountService;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;
import org.bank.user.User;
import org.bank.user.UserService;

import java.util.Scanner;

@RequiredArgsConstructor
public class CloseAccountProcessor implements OperationCommandProcessor {
  private final Scanner scanner;
  private final AccountService accountService;
  private final UserService userService;
  
  @Override
  public void processOperation() {
    System.out.println("Введите ID аккаунта для закрытия");
    int accountId = Integer.parseInt(scanner.nextLine());
    Account account = this.accountService.closeAccount(accountId);
    
    User user = this.userService.findUserById(account.getUserId())
      .orElseThrow(() -> new IllegalArgumentException(String.format("Пользователь с ID=%d не найден", accountId)));
    user.getAccountList().remove(account);
  }
  
  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.ACCOUNT_CLOSE;
  }
}
