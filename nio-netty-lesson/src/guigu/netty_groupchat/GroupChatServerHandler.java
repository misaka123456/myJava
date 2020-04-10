package guigu.netty_groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHandler extends SimpleChannelInboundHandler {

    // 定义一个channel组，管理所有的channel
    // GlobalEventExecutor是全局事件执行器（单例）
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 链接建立后第一个执行
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将客户加入聊天组后推送所有客户
        channels.writeAndFlush("[客户端]" + channel.remoteAddress() + " 加入聊天室");
        channels.add(channel);
    }

    // channel处于活跃状态时调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("[客户端]" + channel.remoteAddress() + " 上线了");
        System.out.println(ctx.channel().remoteAddress() + " 上线了");
    }

    // 不活跃时调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离线了");
        System.out.println(ctx.channel().remoteAddress() + " 离线了");
    }

    // 断开连接时调用
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.remove(channel); // 可以自动去掉？
        channels.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开聊天室");
        System.out.println("当前人数： " + channels.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        Channel channel = ctx.channel();
        channels.forEach(ch -> {
            if (ch != channel) {
                // 排出自己,转发消息
                ch.writeAndFlush(sdf.format(new Date()) + "\n" + "[客户端]" + channel.remoteAddress() + "： " + msg + "\n");
            } else {
                ch.writeAndFlush(sdf.format(new Date()) + "\n" + "我： " + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
