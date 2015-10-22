package evershall.netty;

import static io.netty.handler.logging.LogLevel.INFO;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

public class TelnetServer {

   static final int PORT = Integer.parseInt(System.getProperty("port", "8023"));

   public static void main(final String[] args) throws Exception {

      final EventLoopGroup bossGroup = new EpollEventLoopGroup(1);
      final EventLoopGroup workerGroup = new EpollEventLoopGroup();

      try {
         final ServerBootstrap b = new ServerBootstrap();
         b.group(bossGroup, workerGroup).channel(EpollServerSocketChannel.class).handler(new LoggingHandler(INFO)).childHandler(new TelnetServerInitializer());

         b.bind(PORT).sync().channel().closeFuture().sync();

      }
      finally {
         bossGroup.shutdownGracefully();
         workerGroup.shutdownGracefully();
      }
   }

}
