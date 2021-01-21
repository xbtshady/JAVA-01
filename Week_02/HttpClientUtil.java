package java0.nio01;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    public static void main(String[] args) {
        System.out.println(getHttpResult("http://localhost:8801"));
    }
    public static String getHttpResult(String url) {
        String result = "";
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        try {
            response = HttpClients.createDefault().execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }
}
