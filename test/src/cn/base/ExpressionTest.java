package cn.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionTest {



    public static void main(String[] args) {


        System.out.println("abc".matches("abc"));
        System.out.println("abccsdf".matches(".*.{2}.*"));

        System.out.println(Pattern.matches("abc", "a"));

        System.out.println("---------------------");
        Matcher matcher = Pattern.compile("a(.+?)(\\d)(\\2+)(.*)").matcher("abcdef1111111ssadfsadf");
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
        }
        System.out.println("---------");
        System.out.println("abcdef1111111ssadfsadf".replaceAll("a(.+?)(\\d)(\\2+)(.*)", "$3"));

    }



}
