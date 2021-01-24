package top.xmindguoguo.spring.beans.propertyeditors;

import java.beans.PropertyEditorSupport;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 22:10
 * @Version: v1.0
 */
public class AnimalPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return null;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    }
}
