package de.samples.annotations;

public class Start {

  public static void main(String[] args) {
    var person = new Person("John");
    // System.out.println(person.sayHello());
    var framework = new Miniframework();
    framework.greetings(person);
  }

}
