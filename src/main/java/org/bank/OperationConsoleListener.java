package org.bank;

import lombok.RequiredArgsConstructor;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;

import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class OperationConsoleListener {
  
  private final Scanner scanner;
  private final Map<ConsoleOperationType, OperationCommandProcessor> processors;
  
  public void listenUpdates() {
    System.out.println("Please type operation: \n");
    
    while (true) {
      ConsoleOperationType operationType = listenNextOperation();
      processNextOperation(operationType);
    }
  }
  
  private ConsoleOperationType listenNextOperation() {
    System.out.println("Введите команду: ");
    while (true) {
      var nextOperation = scanner.nextLine();
      try {
        return ConsoleOperationType.valueOf(nextOperation);
      } catch (IllegalArgumentException e) {
        System.out.println("Не найдена команда");
      }
    }
  }
  
  private void processNextOperation(ConsoleOperationType operation) {
    try {
      var processor = processors.get(operation);
      processor.processOperation();
    } catch (Exception e) {
      System.err.printf("Ошибка. %s\n", e.getMessage());
    }
  }
}