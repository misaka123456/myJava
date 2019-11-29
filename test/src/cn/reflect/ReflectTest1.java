package cn.reflect;


import cn.model.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectTest1 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // 1

        Class<Person> pc = Person.class;

        Person p1 = pc.newInstance();
        System.out.println(p1);

        Constructor<Person> constructor = pc.getConstructor(String.class, int.class);

        Person p2 = constructor.newInstance("校长", 12);
        System.out.println(p2);

        Method[] methods = pc.getMethods();
        System.out.println(Arrays.toString(methods));

        Method printAge = pc.getMethod("printAge");
        Method printAge2 = pc.getMethod("printAge");
        printAge.invoke(p2);

        System.out.println(printAge.equals(printAge2));


    }

}
