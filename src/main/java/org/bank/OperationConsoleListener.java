package org.bank;

import lombok.RequiredArgsConstructor;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;

import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class OperationConsoleListener {
  private final Scanner scanner;
  private final Map<ConsoleOperationType, OperationCommandProcessor> processorMap;
  
  public void listenUpdates() {
    System.out.println("Введите команду: \n");
    
    while (true) {
      ConsoleOperationType operationType = listenNextOperation();
      processNextOperation(operationType);
    }
  }
  
  private ConsoleOperationType listenNextOperation() {
    System.out.println("\nВведите команду: ");
    printAllAvailableOperation();
    System.out.println();
    
    while (true) {
      String nextOperation = scanner.nextLine();
      try {
        return ConsoleOperationType.valueOf(nextOperation);
      } catch (IllegalArgumentException e) {
        System.out.println("Не найдена команда");
      }
    }
  }
  
  private void printAllAvailableOperation() {
    processorMap.keySet()
      .forEach(System.out::println);
  }
  
  private void processNextOperation(ConsoleOperationType operation) {
    try {
      OperationCommandProcessor processor = processorMap.get(operation);
      processor.processOperation();
    } catch (Exception e) {
      System.err.printf("Ошибка. %s\n", e.getMessage());
    }
  }
}