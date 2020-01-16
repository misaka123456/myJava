package cn.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author xiakai
 * @create 2020-01-16 15:02
 */
public class Test1 {




    public static void main(String[] args) {

        // 有输入有输出：Function
        //      调用：apply
        //      连接：andThen（后续处理）、compose（预处理）
        // 有输入无输出：Consumer
        //      调用：accept
        //      连接：andThen（继续处理）
        // 无输入有输出：Supplier
        //      调用：get
        //      连接：无
        // boolean输出：Predicate
        //      调用：test
        //      连接：or（或）、and（与）、negate（非）

        Function<Integer, Integer> intToStrToLenToAdd100 = ((Function<String, Integer>) String::length).
                andThen((i) -> i+ 100).
                compose(String::valueOf);
        System.out.println(intToStrToLenToAdd100.apply(123456789));

//        Consumer<Integer> consumer = System.out::println;
//        Consumer<Integer> consumer1 = consumer.andThen((integer -> System.out.println(integer + 100)));
        Consumer<Integer> print = ((Consumer<Integer>) System.out::println).andThen(System.out::println);
        print.accept(1);


        System.out.println("---------------------");
        Supplier<String> s = () -> "666";
        System.out.println(s.get());


        Predicate<Integer> p = ((Predicate<Integer>) integer -> integer < 100).and(integer -> integer > 50).or(i -> i == 1000);
        System.out.println(p.test(51));
        System.out.println(p.test(999));
        System.out.println(p.test(1000));



    }



}
