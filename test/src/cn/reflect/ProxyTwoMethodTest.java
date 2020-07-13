package cn.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiakai
 * @create 2020-07-12 12:29
 */
public class ProxyTwoMethodTest {

    public static void main(String[] args) {
        MyHandler<SubjectImpl> proxy = new MyHandler<>(new SubjectImpl());
        Subject obj = proxy.getProxyObj();
        obj.method1();

    }


    static class MyHandler<T> implements InvocationHandler {

        T obj;

        public MyHandler(T t) {
            obj = t;
        }

        @SuppressWarnings("unchecked")
        public T getProxyObj() {
            return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (method.getName().equals("method1")) {
                System.out.println("method 1 pre handler");
                method.invoke(obj, args);
                System.out.println("method 1 post handler");
            } else if (method.getName().equals("method2")) {
                System.out.println("method 2 pre handler");
                method.invoke(obj, args);
                System.out.println("method 2 post handler");
            }
            return null;
        }
    }


    interface Subject {
        void method1();
        void method2();
    }

    static class SubjectImpl implements Subject {

        @Override
        public void method1() {
            System.out.println("method 1 is run");
            method2();
        }

        @Override
        public void method2() {
            System.out.println("method 2 is run");
        }
    }

}
