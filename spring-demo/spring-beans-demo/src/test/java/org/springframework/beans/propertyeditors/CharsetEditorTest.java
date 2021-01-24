package org.springframework.beans.propertyeditors;

import org.junit.Test;

import java.beans.PropertyEditor;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 20:18
 * @Version: v1.0
 */
public class CharsetEditorTest {
    @Test
    public void test2() {
        // 虽然都行，但建议你规范书写：UTF-8
        PropertyEditor editor = new CharsetEditor();
        editor.setAsText("UtF-8");
        System.out.println(editor.getAsText()); // UTF-8
        editor.setAsText("utF8");
        System.out.println(editor.getAsText()); // UTF-8
    }
}
