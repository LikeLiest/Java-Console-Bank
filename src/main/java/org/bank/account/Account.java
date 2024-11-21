package org.bank.account;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Account {
  private final int id;
  private final int userId;
  private int moneyAmount;
}
