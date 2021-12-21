package xmindguoguo.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/12/22 1:10
 * @Version: v1.0
 */
public class OkHttpMain {
    public static void  main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://pagead2.googlesyndication.com/getconfig/sodar?sv=200&tid=gda&tv=r20211207&st=env")
                .method("GET", null)
                .addHeader("authority", "pagead2.googlesyndication.com")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("accept", "*/*")
                .addHeader("origin", "https://www.json.cn")
                .addHeader("x-client-data", "CIy2yQEIpLbJAQjAtskBCKmdygEI2dHKAQie+csBCOeEzAEItoXMAQjLicwBCNKPzAEYjp7LAQ==")
                .addHeader("sec-fetch-site", "cross-site")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://www.json.cn/")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .build();
        Response response = client.newCall(request).execute();
        System.err.println(response.body().string());
    }
}
