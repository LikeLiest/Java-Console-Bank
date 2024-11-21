package org.bank.operations;


import org.bank.account.AccountService;
import org.bank.operations.processors.*;
import org.bank.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OperationProcessorsConfiguration {
  @Bean
  public CreateAccountProcessor createAccountProcessor(
    Scanner scanner,
    UserService userService,
    AccountService accountService
  ) {
    return new CreateAccountProcessor(userService, accountService, scanner);
  }
  
  @Bean
  public CreateUserProcessor createUserProcessor(Scanner scanner, UserService userService) {
    return new CreateUserProcessor(scanner, userService);
  }
  
  @Bean
  public ShowAllUsersProcessor showAllUsersProcessor(UserService userService) {
    return new ShowAllUsersProcessor(userService);
  }
  
  @Bean
  public AccountDepositProcessor accountDepositProcessor(AccountService accountService, Scanner scanner) {
    return new AccountDepositProcessor(scanner, accountService);
  }
  
  @Bean
  public AccountTransferProcessor accountTransferProcessor(AccountService accountService, Scanner scanner) {
    return new AccountTransferProcessor(scanner, accountService);
  }
  
  @Bean
  public CloseAccountProcessor closeAccountProcessor(Scanner scanner, UserService userService, AccountService accountService) {
    return new CloseAccountProcessor(scanner, accountService, userService);
  }
  
  @Bean
  public AccountWithdrawProcessor accountWithdrawProcessor(AccountService accountService, Scanner scanner) {
    return new AccountWithdrawProcessor(accountService, scanner);
  }
}
