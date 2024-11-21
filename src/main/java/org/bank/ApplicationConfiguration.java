package org.bank;


import org.bank.account.AccountService;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;
import org.bank.operations.processors.CreateAccountProcessor;
import org.bank.operations.processors.CreateUserProcessor;
import org.bank.operations.processors.ShowAllUsersProcessor;
import org.bank.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Configuration
public class ApplicationConfiguration {
  
  @Bean
  public Scanner scanner() {
    return new Scanner(System.in);
  }
  
  @Bean
  public OperationConsoleListener operationConsoleListener(
    Scanner scanner,
    CreateUserProcessor createUserProcessor,
    CreateAccountProcessor createAccountProcessor,
    ShowAllUsersProcessor showAllUsersProcessor
  ) {
    Map<ConsoleOperationType, OperationCommandProcessor> map =
      Map.of(
        ConsoleOperationType.USER_CREATE, createUserProcessor,
        ConsoleOperationType.ACCOUNT_CREATE, createAccountProcessor,
        ConsoleOperationType.SHOW_ALL_USERS, showAllUsersProcessor
      );
    return new OperationConsoleListener(scanner, map);
  }
  
  @Bean
  public AccountService accountService() {
    return new AccountService();
  }
  
  @Bean
  public UserService userService(AccountService accountService) {
    return new UserService(accountService);
  }
}
