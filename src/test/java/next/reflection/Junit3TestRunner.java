package next.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Junit3TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;
        final Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.getName().matches("test.*"))
                .forEach(method -> {
                    try {
                        method.invoke(clazz.newInstance());
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        e.printStackTrace();
                    }
                });
    }
}
