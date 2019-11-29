package cn.reflect;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MHTest {

    public static void main(String[] args) throws Throwable {

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class, char.class, char.class);

        MethodHandle replace = lookup.findVirtual(String.class, "replace", methodType);

        String s = (String) replace.invokeExact("abc", 'b', 'd');

        System.out.println(s);

    }



}
