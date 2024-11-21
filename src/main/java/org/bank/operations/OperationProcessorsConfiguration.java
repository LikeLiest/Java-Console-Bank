package org.bank.operations;


import org.bank.account.AccountService;
import org.bank.operations.processors.CreateAccountProcessor;
import org.bank.operations.processors.CreateUserProcessor;
import org.bank.operations.processors.ShowAllUsersProcessor;
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
}
