package tests.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class Main {

  public static void main(String[] args) {
    try {
      start(8082);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void start(int port) throws Exception {
    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup worker = new NioEventLoopGroup();

    ServerBootstrap serverBootstrap = new ServerBootstrap();
    try {

      serverBootstrap.channel(NioServerSocketChannel.class)
              .group(boss, worker)
              .childOption(ChannelOption.SO_KEEPALIVE, true)
              .option(ChannelOption.SO_BACKLOG, 1024)
              .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                  ch.pipeline().addLast("http-decoder", new HttpServerCodec());
                  ch.pipeline().addLast(new HttpServerHandler());
                }
              });

      ChannelFuture future = serverBootstrap.bind(port).sync();
      future.channel().closeFuture().sync();
    } finally {
      boss.shutdownGracefully();
      worker.shutdownGracefully();
    }

  }
}
