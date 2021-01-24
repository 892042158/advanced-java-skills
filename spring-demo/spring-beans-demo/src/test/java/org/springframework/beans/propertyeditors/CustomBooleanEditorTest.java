package org.springframework.beans.propertyeditors;

import org.junit.Test;

import java.beans.PropertyEditor;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 20:15
 * @Version: v1.0
 * @see https://cloud.tencent.com/developer/article/1758362
 */
public class CustomBooleanEditorTest {

    @Test
    public void test1() {
        PropertyEditor editor = new CustomBooleanEditor(true);

        // 这些都是true，不区分大小写
        editor.setAsText("trUe");
        System.out.println(editor.getAsText());
        editor.setAsText("on");
        System.out.println(editor.getAsText());
        editor.setAsText("yes");
        System.out.println(editor.getAsText());
        editor.setAsText("1");
        System.out.println(editor.getAsText());

        // 这些都是false（注意：null并不会输出为false，而是输出空串）
        editor.setAsText(null);
        System.out.println(editor.getAsText());
        editor.setAsText("fAlse");
        System.out.println(editor.getAsText());
        editor.setAsText("off");
        System.out.println(editor.getAsText());
        editor.setAsText("no");
        System.out.println(editor.getAsText());
        editor.setAsText("0");
        System.out.println(editor.getAsText());

        // 报错
        editor.setAsText("2");
        System.out.println(editor.getAsText());
    }
}
