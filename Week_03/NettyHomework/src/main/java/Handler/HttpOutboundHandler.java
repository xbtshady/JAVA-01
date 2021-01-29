package Handler;

import filter.HeaderHttpRequestFilter;
import filter.HeaderHttpResponseFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import router.HttpRouter;

import java.util.List;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
public class HttpOutboundHandler {
    private List<String> backendUrls;
    private HttpRouter router;
    private HeaderHttpResponseFilter responseFilter = new HeaderHttpResponseFilter();

    public HttpOutboundHandler(List<String> backendUrls){
        this.backendUrls = backendUrls;
        this.router = new HttpRouter(backendUrls);
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HeaderHttpRequestFilter filter)throws Exception{
        String backendUrl = router.route();
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        httpGet(fullRequest,ctx,url);
    }

    private void httpGet(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx, final String url)throws Exception{
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        FullHttpResponse lastResponse = null;
        try {
            response = (HttpResponse) HttpClients.createDefault().execute(request);
            byte[] body = EntityUtils.toByteArray(response.getEntity());
            lastResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            lastResponse.headers().set("Content-Type", "application/json");
            lastResponse.headers().setInt("Content-Length", Integer.parseInt(response.getFirstHeader("Content-Length").getValue()));
            responseFilter.filter(lastResponse);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(lastResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(lastResponse);
                }
            }
            ctx.flush();
        }
    }


}