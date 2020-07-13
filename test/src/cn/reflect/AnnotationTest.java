package cn.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiakai
 * @create 2020-07-12 12:48
 */
public class AnnotationTest {


    public static void main(String[] args) throws NoSuchMethodException {

        fruit apple = new Apple();
        Class<? extends fruit> aClass = apple.getClass();
        fruit o = (fruit) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), (proxy, method, args1) -> {
            if (method.getName().equals("eat")) {
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                String value = annotation.value();
                System.out.println(value);
                method.invoke(apple);
            }
            return null;
        });

        o.eat();


    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface MyAnnotation {
        String value() default "baba";
    }

    interface fruit {
        @MyAnnotation("111")
        void eat();
    }

    static class Apple implements fruit{
        public void eat() {
            System.out.println("apple is eaten");
        }

    }

}
