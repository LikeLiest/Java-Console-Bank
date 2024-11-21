package org.bank.operations.processors;

import lombok.RequiredArgsConstructor;
import org.bank.account.AccountService;
import org.bank.operations.ConsoleOperationType;
import org.bank.operations.OperationCommandProcessor;

import java.util.Scanner;

@RequiredArgsConstructor
public class AccountWithdrawProcessor implements OperationCommandProcessor {
  private final AccountService accountService;
  private final Scanner scanner;
  
  @Override
  public void processOperation() {
    System.out.println("Введите ID аккаунта");
    int accountId = Integer.parseInt(scanner.nextLine());
    System.out.println("Введите сумму");
    int amountToWithdraw = Integer.parseInt(scanner.nextLine());
    this.accountService.withdrawFromAccount(accountId, amountToWithdraw);
    System.out.printf("Успешно списались деньги с аккаунта ID=%d на сумму: %d", accountId, amountToWithdraw);
  }
  
  @Override
  public ConsoleOperationType getOperationType() {
    return ConsoleOperationType.ACCOUNT_WITHDRAW;
  }
}
