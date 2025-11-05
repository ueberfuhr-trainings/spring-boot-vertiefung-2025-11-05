package de.samples.annotations;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Person {

  private final String name;


  @SayHello
  public String sayHello() {
    return "Hello " + name;
  }

  @SayHello(prefix = "(Auf Deutsch) ")
  public String sayHelloGerman() {
    return "Hallo, ich bin " + name;
  }

}
