package cn.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerTest implements InvocationHandler {

    private Object obj;

    public InvocationHandlerTest(Subject subject) {
        this.obj = subject;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("前置处理");
        Object result = method.invoke(obj, args);
        System.out.println("后置处理");
        return result;
    }

    public static void main(String[] args) {

        SubjectA subjectA = new SubjectA();
        System.out.println("被代理类： " + subjectA.getClass());
        ClassLoader classLoader = subjectA.getClass().getClassLoader();
        Class<?>[] interfaces = subjectA.getClass().getInterfaces();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandlerTest(subjectA));
        System.out.println("代理类： " + subject.getClass());
        subject.sayHallo("ok");

    }


    interface Subject {
        public void sayHallo(String s);
    }

    static class SubjectA implements Subject {

        @Override
        public void sayHallo(String s) {
            System.out.println("SubjectA say hallo : " + s);
        }
    }
}
