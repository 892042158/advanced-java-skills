package top.xmindguoguo.java8.jvmtest;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * @ClassName HeapOOM
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年5月11日 下午3:09:36
 *
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
