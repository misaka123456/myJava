package guigu.websocket;

import guigu.netty_groupchat.GroupChatServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer {


    private final int port;

    private MyServer(int port) {
        this.port = port;
    }

    private void run() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler())
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // 基于http协议的双工通信
                            // http编解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 以块方式添加ChunkedWriteHandler处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /*
                            http数据传输时是分段的
                            HttpObjectAggregator可以将多个段聚合
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                            websocket数据是以帧的形式进行传递
                            请求时： ws://localhost:7000/xxx 表示请求的uri
                            WebSocketServerProtocolHandler将http协议升级为ws协议，保持长链接
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                            // 自定义handler
                            pipeline.addLast(new MyServerWSFrameHandler());
                        }
                    });
            System.out.println("netty开始启动");
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("netty启动成功");
            channelFuture.channel().closeFuture().sync();
            System.out.println("netty已关闭");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new MyServer(7000).run();
    }
}
