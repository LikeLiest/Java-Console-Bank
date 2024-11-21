package org.bank.operations.processors;

import lombok.RequiredArgsConstructor;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;
import org.bank.user.User;
import org.bank.user.UserService;

import java.util.Scanner;

@RequiredArgsConstructor
public class CreateUserProcessor implements OperationCommandProcessor {
  private final Scanner scanner;
  private final UserService userService;
  
  @Override
  public void processOperation() {
    System.out.println("Введите логин: ");
    String login = scanner.nextLine();
    
    User user = this.userService.createUser(login);
    System.out.printf("Пользователь успешно создан: %s\n", user.toString());
  }
  
  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.USER_CREATE;
  }
}
