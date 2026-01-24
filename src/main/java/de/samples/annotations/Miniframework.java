package de.samples.annotations;

import lombok.SneakyThrows;

import java.lang.reflect.Method;

public class Miniframework {

  @SneakyThrows
  public void greetings(Object object) {

    if (null == object) {
      return;
    }

    // Suche nach Methoden, die mit @SayHello annotiert sind
    Method[] methods = object.getClass().getDeclaredMethods();

    for (Method method : methods) {
      if (method.isAnnotationPresent(SayHello.class)) {

        SayHello meta = method.getAnnotation(SayHello.class);
        String prefix = meta.prefix();
        // Rufe diese Methoden auf - keine Parameter erlaubt!
        Object returnValue = method.invoke(object);
        // Den Rückgabewert schreibe auf Konsole
        System.out.println(prefix + returnValue);

      }
    }


  }

}
