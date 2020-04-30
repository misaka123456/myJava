package cn.exception;

public class MyException extends Exception {

    public MyException() {

    }
    public MyException(String msg) {
        super(msg);
        System.out.println("my exception : " + msg);
    }
}
