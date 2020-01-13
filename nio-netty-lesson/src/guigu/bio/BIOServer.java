package guigu.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws IOException {


        // 创建线程池
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(666);

        System.out.println("服务器启动了");

        while (true) {
            System.out.println("等待链接。。。");   // 阻塞
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端了");

            // 创建线程
            newCachedThreadPool.execute(() -> {
                // 如果有客户连接，就通信
                handler(socket);
            });
        }




    }

    public static void handler(Socket socket) {

        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            int len;
            System.out.println("显示客户端" + Thread.currentThread().getId() + "发过来的数据： ");
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println(Thread.currentThread().getName() + ": " + new String(bytes, 0, len));
                System.out.println("read...");   // 阻塞
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭与客户端的链接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }



}
