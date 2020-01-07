package cn.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {


    public static void main(String[] args) throws IOException {

        // 创建 Server Socket
        ServerSocket server = new ServerSocket();
        InetSocketAddress address = new InetSocketAddress("localhost", 10032);
        // 绑定地址和端口
        server.bind(address);
        // 接受通信
        Socket socket = server.accept();
        // 在没有客户端对其进行相应前，下面的代码不会执行，将一直阻塞

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader keyword = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            if (reader.ready()) {
                String info = reader.readLine();
                System.out.println("Server接受到: " + info);
            }
            if (keyword.ready()) {
                String message = keyword.readLine();
                writer.println(message);
                System.out.println("Server发送： " + message);
            }
        }
    }

}
