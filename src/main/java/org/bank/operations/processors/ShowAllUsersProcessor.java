package org.bank.operations.processors;

import lombok.RequiredArgsConstructor;
import org.bank.operations.OperationCommandProcessor;
import org.bank.user.UserService;

@RequiredArgsConstructor
public class ShowAllUsersProcessor implements OperationCommandProcessor {
  private final UserService userService;
  
  @Override
  public void processOperation() {
    this.userService.findAllUsers().forEach(System.out::println);
  }
}
