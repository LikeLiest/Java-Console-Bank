package org.bank.operations.processors;

import lombok.RequiredArgsConstructor;
import org.bank.account.Account;
import org.bank.account.AccountService;
import org.bank.exception.UserNotFoundException;
import org.bank.operations.OperationCommandProcessor;
import org.bank.user.User;
import org.bank.user.UserService;

import java.util.Scanner;

@RequiredArgsConstructor
public class CreateAccountProcessor implements OperationCommandProcessor {
  private final UserService userService;
  private final AccountService accountService;
  private final Scanner scanner;
  
  @Override
  public void processOperation() {
    System.out.println("Введие ID пользователя для создания аккаунта: ");
    int userId = Integer.parseInt(scanner.nextLine());
    
    User user = this.userService.findUserById(userId)
      .orElseThrow(() -> new UserNotFoundException(String.format("Пользователь: с id=%d не найден", userId)));
    Account account = this.accountService.createAccount(user);
    user.getAccountList().add(account);
    
    System.out.printf("Новый аккаунт создан с ID: %d для пользователья %s%n", userId, account.toString());
  }
}
