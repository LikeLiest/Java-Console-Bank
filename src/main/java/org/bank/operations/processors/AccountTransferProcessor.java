package org.bank.operations.processors;

import lombok.RequiredArgsConstructor;
import org.bank.account.AccountService;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;

import java.util.Scanner;

@RequiredArgsConstructor
public class AccountTransferProcessor implements OperationCommandProcessor {
  private final Scanner scanner;
  private final AccountService accountService;
  
  @Override
  public void processOperation() {
    System.out.println("Введите ID аккаунта");
    int fromAccountId = Integer.parseInt(scanner.nextLine());
    
    System.out.println("Введите ID получателя");
    int toAccountId = Integer.parseInt(scanner.nextLine());
    
    System.out.println("Введите сумму для перевода");
    int amountToTransfer = Integer.parseInt(scanner.nextLine());
    
    this.accountService.transfer(fromAccountId, toAccountId, amountToTransfer);
    System.out.printf("Успешный перевод из аккаунта с ID=%d в аккаунт с ID=%d - на сумму %d с коммисией 1%%.", fromAccountId, toAccountId, amountToTransfer);
  }
  
  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.ACCOUNT_TRANSFER;
  }
}
