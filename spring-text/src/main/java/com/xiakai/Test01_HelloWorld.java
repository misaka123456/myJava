package com.xiakai;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiakai
 * @create 2020-06-17 10:30
 */
public class Test01_HelloWorld {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/bean.xml");
        Hello helloBean = (Hello) context.getBean("helloBean");
        System.out.println(helloBean.hello());


    }

}
