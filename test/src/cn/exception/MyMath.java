package cn.exception;

/**
 * @author xiakai
 * @create 2020-07-19 19:15
 */
public class MyMath {


    public static int add(int a, int b) {
        return a + b;
    }

    public static int div(int a, int b) throws MyMathException {
        if (b == 0) {
            throw new MyMathException("bei div shu can not be zero ");
        }
        return a / b;
    }

}
