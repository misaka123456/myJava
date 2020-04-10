package guigu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Test01_NettyServer {

    public static void main(String[] args) throws InterruptedException {

        // boss管连接，worker管业务，都是无限循环
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {


            // 服务器启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boosGroup, workerGroup) // 设置两个线程
                    .channel(NioServerSocketChannel.class) // 设置通道实现方式
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列等待连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 创建通道初始化对象
                        // 给pipeline设置处理器handler
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new Test01_NettyServerHandler());
                        }
                    }); // workergroup的eventloop对应的管道设置处理器


            System.out.println("。。。服务器准备好了");

            // 帮顶端口并同步
            // 启动服务器
            ChannelFuture cf = bootstrap.bind(6668).sync();


            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }




    }



}
