package router;

import util.ConfigUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HttpRouter {
    private final String routSet;
    //轮询计数
    private static Integer pos = 0;

    //加权轮询计数
    private static Integer weight_pos = 0;

    //url列表
    private static List<String> urls;

    //加权后的url列表
    private static List<String> WeightUrls;

    public HttpRouter(List<String> urls) {
        //读取配置初始化路由设置
        routSet = ConfigUtils.getValueByKey("routSet");
        //初始化url列表
        this.urls = urls;
        //初始化加权url列表
        this.WeightUrls = this.getWeightUrls(urls);
    }

    public String route() {
        int routSetInt = Integer.parseInt(routSet);
        String result = "";
        switch (routSetInt) {
            case 1:
                result = random();
                break;
            case 2:
                result = weight();
                break;
            case 3:
                result = roundRobin();
                break;
            default:
                break;
        }
        return result;
    }

    //实现随机
    String random() {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }

    //实现轮询
    String roundRobin() {
        String result;
        synchronized (pos) {
            if (pos > urls.size())
                pos = 0;
            result = urls.get(pos);
            pos++;
        }
        return result;
    }

    //实现权重
    String weight() {
        String result = "";
        synchronized (weight_pos) {
            if (weight_pos > WeightUrls.size())
                weight_pos = 0;
            result = WeightUrls.get(weight_pos);
            weight_pos++;
        }
        return result;
    }

    /**
     * 获得加权之后的url列表
     * eg: weights:3,4 urls:url1,url2
     * 结果：weightList：url1,url1,url1,url2,url2,url2,,url2
     */
    private List<String> getWeightUrls(List<String> urls) {
        if (2 != Integer.valueOf(routSet)) {
            return null;
        }
        //读取加权配置
        String weights = ConfigUtils.getValueByKey("urlWeights");
        String[] weightList = weights.split(",");
        List<String> newUrls = new ArrayList<String>();
        int j = 0;
        for (String wightStr : weightList) {
            Integer wight = Integer.valueOf(wightStr);
            String url = urls.get(j);
            for (int i = 0; i < wight; i++) {
                newUrls.add(url);
            }
            j++;
        }
        return newUrls;
    }
}
