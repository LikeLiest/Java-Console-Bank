package org.bank;


import org.bank.account.AccountService;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;
import org.bank.operations.processors.CreateAccountProcessor;
import org.bank.operations.processors.CreateUserProcessor;
import org.bank.operations.processors.ShowAllUsersProcessor;
import org.bank.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
  
  @Bean
  public Scanner scanner() {
    return new Scanner(System.in);
  }
  
  @Bean
  public OperationConsoleListener operationConsoleListener(
    Scanner scanner,
    List<OperationCommandProcessor> commandProcessorList
  ) {
    Map<ConsoleOperationType, OperationCommandProcessor> map =
      commandProcessorList
        .stream()
        .collect(
          Collectors.toMap(
            OperationCommandProcessor::getOperationType,
            processor -> processor
          ));
    
    return new OperationConsoleListener(scanner, map);
  }
  
  @Bean
  public AccountService accountService(
    @Value("${account.default-amount}") int defaultAmount,
    @Value("${account.transfer-commission}") double transferCommission
  ) {
    return new AccountService(defaultAmount, transferCommission);
  }
  
  @Bean
  public UserService userService(AccountService accountService) {
    return new UserService(accountService);
  }
}
