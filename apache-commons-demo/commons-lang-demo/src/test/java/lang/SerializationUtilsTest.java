package lang;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SerializationUtils;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * 序列化的工具类进行测试
 *
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @ClassName SerializationUtilsTest
 * @date 2019年3月13日 下午3:41:35
 */
@Slf4j
public class SerializationUtilsTest {
    UserDO userDO = UserDO.builder().name("小明").age(10).build();

    /**
     * 使用SerializationUtils对UserDO进行深度克隆
     */
    @Test
    public void testClone() {
        UserDO userDO = UserDO.builder().name("小明").age(10).build();
        // 深度克隆
        UserDO cloneUserDO = (UserDO) SerializationUtils.clone(userDO);
        // 验证
        log.info("original UserDO={} ", JSON.toJSONString(userDO));
        log.info("clone UserDO={} ", JSON.toJSONString(cloneUserDO));
        log.info(String.valueOf(userDO == cloneUserDO));
    }

    /**
     * 对象的序列化与反序列化
     *
     * @Title testSerializeAndDeserialize
     * @author 于国帅
     * @date 2019年3月13日 下午3:44:15 void
     */
    @Test
    public void testSerializeAndDeserialize() {
        // 序列化为byte数组
        byte[] bytes = SerializationUtils.serialize(userDO);
        // 反序列化对象
        UserDO deserializeUserDO = (UserDO) SerializationUtils.deserialize(bytes);
        log.info("deserializeUserDO={}", deserializeUserDO);
    }

    // 测试数据库的序列化错误问题
    @Test
    public void testError() throws UnsupportedEncodingException {
        String str = "[\"com.xmindguoguo.boot.core.shiro.MysqlSessionDaoLAST_SYNC_DB_TIMESTAMP\"],\"valid\":true}";
        System.err.println(SerializationUtils.deserialize(new BufferedInputStream(new ByteArrayInputStream(str.getBytes("UTF-8")))));

    }


    public void getEncoding(String str) throws UnsupportedEncodingException {
        System.out.println(str);
        System.out.println(str.getBytes());
        System.out.println(str.getBytes("GB2312"));
        System.out.println(str.getBytes("ISO8859_1"));
        System.out.println(new String(str.getBytes()));
        System.out.println(new String(str.getBytes(), "GB2312"));

        System.out.println(new String(str.getBytes(), "ISO8859_1"));

        System.out.println(new String(str.getBytes("GB2312")));

        System.out.println(new String(str.getBytes("GB2312"), "GB2312"));

        System.out.println(new String(str.getBytes("GB2312"), "ISO8859_1"));

        System.out.println(new String(str.getBytes("ISO8859_1")));

        System.out.println(new String(str.getBytes("ISO8859_1"), "GB2312"));

        System.out.println(new String(str.getBytes("ISO8859_1"), "ISO8859_1"));
    }
}

//需要实现序列化接口
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserDO implements Serializable {

    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

}