package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SimpleChatServer {
    private final int port; //定义服务器端监听的端口

    public SimpleChatServer(int port){
        this.port = port;
    }


    public void start() throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)//指定使用一个NIO传输Channel
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new SimpleChatServerInitializer());
            //异步的绑定服务器,sync()一直等到绑定完成.
            ChannelFuture future = serverBootstrap.bind(this.port).sync();
            System.out.println(SimpleChatServerHandler.class.getName()+" started and listen on '"+ future.channel().localAddress());
            future.channel().closeFuture().sync();//获得这个channel的CloseFuture,阻塞当前线程直到关闭操作完成
        } finally {
            boss.shutdownGracefully().sync();
            worker.shutdownGracefully().sync();
        }
    }



    public static void main(String[] args) throws Exception {
        new SimpleChatServer(8000).start();
    }

}
