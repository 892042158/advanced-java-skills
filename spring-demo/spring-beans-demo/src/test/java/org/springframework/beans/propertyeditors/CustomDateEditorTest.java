package org.springframework.beans.propertyeditors;

import org.junit.Test;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 20:21
 * @Version: v1.0
 */
public class CustomDateEditorTest {
    /**
     * 关注点：这个时间/日期转换器很不好用，构造时就必须指定一个SimpleDateFormat格式化器。在实际应用中，Spring并没有使用到它，而是用后面会说到的替代方案。
     */
    @Test
    public void test3() {
        PropertyEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),true);
        editor.setAsText("2020-11-30 09:10:10");
        System.out.println(editor.getAsText()); // 2020-11-30 09:10:10

        // null输出空串
        editor.setAsText(null);
        System.out.println(editor.getAsText());

        // 报错
        editor.setAsText("2020-11-30");
        System.out.println(editor.getAsText());
    }
}
