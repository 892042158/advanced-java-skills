package top.xmindguoguo.httpclient.ext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@Slf4j
public class HttpUtil {
    /**
     * 通过http请求把url转换为jsonObject
     */
    public static JSONObject getJSONObject(String url) {
        HttpGet httpget = new HttpGet(url); // 使用get请求，如果是post请使用HttpPost
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {
                String result = EntityUtils.toString(resEntity, "UTF-8");
                JSONObject resObj = (JSONObject) JSON.parse(result);
                return resObj;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 通过http请求把url转换为jsonObject
     */
    public static String getString(String url) {
        HttpGet httpget = new HttpGet(url); // 使用get请求，如果是post请使用HttpPost
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                return EntityUtils.toString(resEntity, "UTF-8");
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }
}
