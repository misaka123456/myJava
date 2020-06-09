import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class OfferTest {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

        System.out.println(list);

    }
}
