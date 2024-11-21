package org.bank.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bank.account.Account;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class User {
  private final int id;
  private final String login;
  private final List<Account> accountList;
}
