package org.bank.user;

import org.bank.account.Account;
import org.bank.account.AccountService;
import org.bank.exception.UserExistsException;

import java.util.*;
import java.util.logging.Logger;

public class UserService {
  
  private final Map<Integer, User> userMap;
  private final Set<String> tokenLogins;
  private final AccountService accountService;
  
  private int idCounter;
  
  private static final Logger logger = Logger.getLogger(UserService.class.getName());
  
  public UserService(AccountService accountService) {
    this.tokenLogins = new HashSet<>();
    this.userMap = new HashMap<>();
    this.idCounter = 0;
    this.accountService = accountService;
    logger.info(String.format("Инициализация объекта UserService: %s %s", this.userMap, this.idCounter));
  }
  
  public User createUser(String login) {
    if(login.length() <= 2)
      throw new IllegalArgumentException("Логин не может быть меньше чем 4 символа");
    
    if(!login.matches("^[a-zA-Z][a-zA-Z0-9]*$"))
      throw new IllegalArgumentException("Логин должен начинаться с буквы и содержать только буквы или цифры");
    
    if (tokenLogins.contains(login.toLowerCase()))
      throw new UserExistsException(String.format("Пользователь с логином '%s' уже существует", login));
    
    tokenLogins.add(login.toLowerCase());
    idCounter++;
    User newUser = new User(idCounter, login, new ArrayList<>());
    
    Account account = accountService.createAccount(newUser);
    newUser.getAccountList().add(account);
    
    userMap.put(newUser.getId(), newUser);
    return newUser;
  }
  
  public Optional<User> findUserById(int id) {
    return Optional.ofNullable(userMap.get(id));
  }
  
  public List<User> findAllUsers() {
    return List.copyOf(userMap.values());
  }
}
