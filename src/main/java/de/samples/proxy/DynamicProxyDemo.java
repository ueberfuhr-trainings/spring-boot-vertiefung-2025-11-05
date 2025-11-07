package de.samples.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

  interface Person {
    String sayHello();
  }

  static class PersonImpl implements Person {
    @Override
    public String sayHello() {
      return "Hello";
    }
  }


  public static void main(String[] args) {

    Person person = new PersonImpl();

    Person personProxy = (Person) Proxy.newProxyInstance(
      DynamicProxyDemo.class.getClassLoader(),
      new Class[]{Person.class},
      new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          return switch (method.getName()) {
            case "sayHello" -> "Hello from proxy (" + person.sayHello() + ")";
            case "toString" -> "PersonProxy";
            default -> throw new UnsupportedOperationException("Method not supported: " + method);
          };
        }
      }
    );

    System.out.println(person.sayHello());
    System.out.println(personProxy.sayHello());
    System.out.println(personProxy.toString());
    System.out.println(personProxy.hashCode());

  }

}
