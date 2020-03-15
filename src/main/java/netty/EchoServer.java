package netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        //        if (args.length != 1) {
        //            System.err.println("Usage" + EchoServer.class.getSimpleName() + "<port>");
        //            return;
        //        }
        int port = Integer.parseInt("8877");
        new EchoServer(port).start();
    }

    private void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on"
                    + channelFuture.channel().localAddress() + " ===remoteAddress:"
                    + channelFuture.channel().remoteAddress());
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}
