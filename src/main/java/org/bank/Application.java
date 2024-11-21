package org.bank;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
  public static void main(String[] args) {
    // Spring Context будет сканировать эту директорию, и все что внутри нее для создания бинов
    AnnotationConfigApplicationContext context =
      new AnnotationConfigApplicationContext("org.bank");
    
    context.getBean(OperationConsoleListener.class).listenUpdates();
  }
}
