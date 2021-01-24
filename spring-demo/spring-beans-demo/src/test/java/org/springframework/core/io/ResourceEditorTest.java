package org.springframework.core.io;

import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;

import java.beans.PropertyEditor;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 20:25
 * @Version: v1.0
 */
public class ResourceEditorTest {
    /**
     * 关注点：Spring扩展出来的Resource不仅自持常规file:资源协议，还支持平时使用最多的classpath:协议，可谓非常好用。
     */
    @Test
    public void test4() {
        // 支持标准URL如file:C:/myfile.txt，也支持classpath:myfile.txt
        // 同时还支持占位符形式
        PropertyEditor editor = new ResourceEditor(new DefaultResourceLoader(), new StandardEnvironment(), true);

        // file:形式本处略
        // editor.setAsText("file:...");
        // System.out.println(editor.getAsText());

        // classpath形式（注意：若文件不存在不会报错，而是输出null）
        editor.setAsText("classpath:app.properties");
        System.out.println(editor.getAsText()); // 输出带盘符的全路径

        System.setProperty("myFile.name", "app.properties");
        editor.setAsText("classpath:${myFile.name}");
        System.out.println(editor.getAsText()); // 结果同上
    }
}
