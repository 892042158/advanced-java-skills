package org.jsoup;

import org.jsoup.safety.Whitelist;
import org.junit.Test;

/**
 * https://blog.csdn.net/dietime1943/article/details/78470750
 * 
 * @ClassName JsoupTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月16日 下午3:06:21
 *
 */
public class JsoupTest {
    /**
     * Whitelist详细的测试 参考WhitelistTest 类
     * 
     * @Title clean
     * @author 于国帅
     * @date 2019年1月16日 下午3:08:07 void
     */
    @Test
    public void clean() {
        // 清除html
        String html = "<tableclass='layui-table'id='table1'lay-filter='table1'><colgroup><colwidth='150'><colwidth='200'><col></colgroup><thead><tr><th>&lt; td &gt;企业<td 名称</th><th>信用代码</th><th>企业法人</th><th>登记机关</th><th>操作</th></tr></thead>"; // 这里是传递的参数
        String str = Jsoup.clean(html, Whitelist.relaxed());
        System.err.println(str);
        // 我尝试增加 td <td <td s> 除了td 都被清除了 转移符可以&lt; td &gt;

    }
}
