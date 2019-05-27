package netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * @Description: 处理TextWebSocketFrame的channelInboundHandler 《Netty In
 *               Action》P168
 * @Author: shenpeng
 * @Date: 2018/11/12
 */
public class TextWebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ChannelGroup group;

    public TextWebsocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    /**
     * 重写userEventTriggered方法以处理自定义事件
     *
     * @param [ctx, evt]
     * @return void
     * @Author: shenpeng
     * @Date: 2018/11/12
     */
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //如果改事件表示握手成功，则从Channelpipeline中溢出HttpRequestHandler，因为之后将不会再收到任何HTTP消息
        if (evt == WebSocketServerProtocolHandler.HandshakeComplete.class) {
            ctx.pipeline().remove(HttpRequsetHandler.class);
            //通知所有已经连接的Websocket客户端新的客户端已经连接上了
            group.writeAndFlush(new TextWebSocketFrame("Client" + ctx.channel() + "joined"));
            group.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        //增加消息的引用计数，并将它写到ChannelGroup中所有已经连接的客户端
        group.writeAndFlush(msg.retain());
    }
}
