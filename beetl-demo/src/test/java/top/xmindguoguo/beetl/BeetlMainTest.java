package top.xmindguoguo.beetl;

import org.junit.Test;
import top.xmindguoguo.beetl.ext.BeetlUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BeetlMainTest
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/9 2:18
 * @Version: v1.0
 */
public class BeetlMainTest {

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("sourcefieldid", 1234567);
        String sql = "select  id from yt_database_fields  where id = ${sourcefieldid}";
        System.out.println(BeetlUtil.getString(sql, map));
    }
}
