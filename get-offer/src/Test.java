import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) {



        Map<Short, String> map = new HashMap<>();
        for(short i = 0; i <10; i++) {
            map.put(i, String.valueOf(i));
            map.remove(i-1);
        }



        System.out.println(map.size());

    }


}



