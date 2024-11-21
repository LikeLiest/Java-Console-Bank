package org.bank.operations.processors;

import lombok.RequiredArgsConstructor;
import org.bank.account.AccountService;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;

import java.util.Scanner;

@RequiredArgsConstructor
public class AccountDepositProcessor implements OperationCommandProcessor {
  private final Scanner scanner;
  private final AccountService accountService;
  
  @Override
  public void processOperation() {
    System.out.println("Введите ID аккаунта");
    int accountId = Integer.parseInt(scanner.nextLine());
    System.out.println("Введите сумму депозита");
    int amountToDeposit = Integer.parseInt(scanner.nextLine());
    this.accountService.depositAccount(accountId, amountToDeposit);
    System.out.printf("Успех. Внесен депозит пользователю с ID=%d на сумму: %d", accountId, amountToDeposit);
  }
  
  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.ACCOUNT_DEPOSIT;
  }
}
