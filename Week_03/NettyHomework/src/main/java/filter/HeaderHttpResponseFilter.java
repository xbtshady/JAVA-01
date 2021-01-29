package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter {

    public void filter(FullHttpResponse response) {
        //添加时间戳
        response.headers().set("nowTime", System.currentTimeMillis() + "");
        //添加签名
        response.headers().set("sign", "xw");
    }
}
