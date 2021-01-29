package Handler;

import filter.HeaderHttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private HeaderHttpRequestFilter filter = new HeaderHttpRequestFilter();
    private HttpOutboundHandler handler;
    private final List<String> proxyServer;

    public HttpInboundHandler(List<String> proxyServer){
        this.proxyServer = proxyServer;
        handler = new HttpOutboundHandler(proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        handler.handle(fullRequest,ctx,filter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //在读取操作期间，有异常抛出时会调用
        cause.printStackTrace();
        ctx.close();
    }
}
