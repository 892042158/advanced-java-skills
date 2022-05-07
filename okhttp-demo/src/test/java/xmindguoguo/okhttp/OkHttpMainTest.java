package xmindguoguo.okhttp;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2022/5/8 0:35
 * @Version: v1.0
 */
public class OkHttpMainTest {


    @Test
    public void wenming4399() throws IOException {
        String startDate = "20220502";
        String endDate = "20220507";


        //首先抓取数据
        JSONArray startDataList = getJSONArray(startDate);
        Map<String, JSONObject> startDataMap = new HashMap<>();
        for (int i = 0; i < startDataList.size(); i++) {
            JSONObject jsonObject = startDataList.getJSONObject(i);
            String key = jsonObject.getString("pid");
            startDataMap.put(key, jsonObject);
        }
        JSONArray endDateList = getJSONArray(endDate);


        List<WenMingModel> dataList = new ArrayList<>();

        for (int i = 0; i < endDateList.size(); i++) {
            JSONObject jsonObject = endDateList.getJSONObject(i);
            String key = jsonObject.getString("pid");
            JSONObject startJSONObject = startDataMap.get(key);
            if (startJSONObject == null) {
                System.err.println("上周为空不参与计算============" + i);
                System.err.println("excel============" + i);
                System.err.println("startJSONObject============" + startJSONObject);
                System.err.println("jsonObject============" + jsonObject);
                continue;
            }
            WenMingModel model = new WenMingModel();
            model.setT(jsonObject.getLongValue("t"));
            model.setPid(jsonObject.getString("pid"));
            model.setN(jsonObject.getString("n"));
            model.setA(jsonObject.getString("a"));
            model.setR(jsonObject.getLong("r"));
            model.setS(jsonObject.getLong("s"));
            model.setAa(jsonObject.getLong("aa"));

            model.setM(strategyValue(jsonObject.getLongValue("m") , startJSONObject.getLongValue("m")));
            model.setOp(strategyValue(jsonObject.getLongValue("op") , startJSONObject.getLongValue("op")));
            model.setRp(strategyValue(jsonObject.getLongValue("rp") , startJSONObject.getLongValue("rp")));
            model.setPs(strategyValue(jsonObject.getLongValue("ps") , startJSONObject.getLongValue("ps")));

            //综合评分 战功1分 据点1000分 占领分商贸暂时不计算
            long score = model.getM() + model.getOp() * 1000 + model.getRp() * 1000;
            model.setScore(score);
            dataList.add(model);
        }
        try {
            String file = "F:\\wenming4399" + File.separator + "混合评分计算_统计时间" + startDate + "-" + endDate + "_产生时间："+System.currentTimeMillis()+ ".xls";
            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            // 如果这里想使用03 则 传入excelType参数即可
            EasyExcel.write(file, WenMingModel.class).sheet("混合评分计算").doWrite(dataList);
            System.out.println("excel导出成功！file ===" + file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 如果num1 < num2 则认为 是跳盟了 按照最新的计算
     * @param num1
     * @param num2
     * @return
     */
    private long strategyValue(long num1,long num2) {
        if(num1 > num2 ){
            return  num1 - num2; //计算成长
        }
        return num1;//新的联盟分数
    }

    private JSONArray getJSONArray(String date) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://news.dmzgame.com/wiki/wm/rank/api/get_areas_day.php?areas=158&day=" + date + "&zu=")
                .method("GET", null)
                .addHeader("Host", "news.dmzgame.com")
                .addHeader("Connection", "keep-alive")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat")
                .addHeader("content-type", "application/json;charset=utf-8")
                .addHeader("rank-openid", "oZJBP4zdoQGvEgkLZuRCCxupt1ns")
                .addHeader("Referer", "https://servicewechat.com/wxd084ae94056bf1ec/14/page-frame.html")
                .addHeader("Accept-Encoding", "deflate")
                .build();
        Response response = client.newCall(request).execute();
        //然后导出数据
        String json = response.body().string();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject data = jsonObject.getJSONArray("Data").getJSONObject(0);
        JSONArray data_list = data.getJSONArray("data_list");
        return data_list;
    }
}
