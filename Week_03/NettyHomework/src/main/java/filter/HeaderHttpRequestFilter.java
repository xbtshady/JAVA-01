package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter {

    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        //添加时间戳
        fullRequest.headers().set("nowTime", System.currentTimeMillis() + "");
        //添加签名
        fullRequest.headers().set("sign", "xw");
    }
}
