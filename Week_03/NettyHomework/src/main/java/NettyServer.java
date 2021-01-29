import Handler.HttpInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class NettyServer {
    private int port;

    private List<String> proxyServers;

    public NettyServer(int port, List<String> proxyServers) {
        this.port=port;
        this.proxyServers = proxyServers;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            //配置ServerBootstrap
            b.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);


            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        //当一个新的连接被接受的时候 一个新的子Channel将会被创建 该方法将被调用
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //将HttpInboundHandler添加到该Channel的ChannelPipeline中
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new HttpServerCodec());
                            p.addLast(new HttpObjectAggregator(1024 * 1024));
                            p.addLast(new HttpInboundHandler(proxyServers));
                        }
                    });
            //绑定端口，使用sync()阻塞到绑定完成为止
            Channel ch = b.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            //监听closeFuture，sync()阻塞到closeFuture完成为止
            ch.closeFuture().sync();
        } finally {
            //释放资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
