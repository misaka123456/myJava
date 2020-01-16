package cn.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiakai
 * @create 2020-01-16 15:45
 */
public class ListStream {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        Stream<Integer> s1 = list.stream().filter(i -> i % 2 == 0);
        Stream<Integer> s2 = s1.map(i -> i * 2);
        Stream<String> s3 = s2.map(String::valueOf);
        Stream<String> s4 = s3.sorted(String::compareTo);
        List<String> collect = s4.collect(Collectors.toList());
        System.out.println(collect);


//        Integer reduce = s2.reduce(1000, Integer::sum);
//        System.out.println(reduce);


    }

}
