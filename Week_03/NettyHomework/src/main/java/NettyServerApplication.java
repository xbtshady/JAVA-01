import util.ConfigUtils;

import java.util.Arrays;

public class NettyServerApplication {
    public static void main(String[] args) {
        //读取配置文件获取端口和urls
        String proxyPort = ConfigUtils.getValueByKey("port");
        String proxyServers = ConfigUtils.getValueByKey("urls");

        int port = Integer.parseInt(proxyPort);

        //创建网关实例
        NettyServer nettyServer = new NettyServer(port, Arrays.asList(proxyServers.split(",")));

        try {
            //启动网关
            nettyServer.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
