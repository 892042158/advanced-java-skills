package top.xmindguoguo.spring.beans.propertyeditors;

import org.springframework.beans.propertyeditors.UUIDEditor;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 22:33
 * @Version: v1.0
 */
public class PersonCatUUIDEditor extends UUIDEditor {

    private static final String SUFFIX = "_YourBatman";

    @Override
    public String getAsText() {
        return super.getAsText().concat(SUFFIX);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.replace(SUFFIX, "");
        super.setAsText(text);
    }
}
