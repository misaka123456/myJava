package guigu.websocket;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class MyServerWSFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 唯一id
        System.out.println("链接加入: " + ctx.channel().id().asLongText());
        // 不唯一
        System.out.println("链接加入: " + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("链接移除： " + ctx.channel().id().asLongText());
        System.out.println("链接移除： " + ctx.channel().id().asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器端收到消息： " + msg.text());
        // 回复客户端
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间" +
                LocalDateTime.now() + "  " + msg.text()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常： " + cause.getMessage());
        ctx.close();
    }
}
