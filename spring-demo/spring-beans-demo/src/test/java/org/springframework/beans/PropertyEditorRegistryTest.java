package org.springframework.beans;

import org.junit.Test;
import org.springframework.beans.propertyeditors.UUIDEditor;
import top.xmindguoguo.spring.beans.model.Animal;
import top.xmindguoguo.spring.beans.model.Cat;
import top.xmindguoguo.spring.beans.model.Person;
import top.xmindguoguo.spring.beans.propertyeditors.AnimalPropertyEditor;
import top.xmindguoguo.spring.beans.propertyeditors.PersonCatUUIDEditor;

import java.beans.PropertyEditor;
import java.util.UUID;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 21:31
 * @Version: v1.0
 * @see https://cloud.tencent.com/developer/article/1762309
 */
public class PropertyEditorRegistryTest {
    /**
     * 值得注意的是，每次调用API向customEditors添加新元素时，customEditorCache就会被清空，因此因尽量避免在运行期注册编辑器，以避免缓存失效而降低性能
     */
    @Test
    public void test5() {
        PropertyEditorRegistry propertyEditorRegistry = new PropertyEditorRegistrySupport();
        propertyEditorRegistry.registerCustomEditor(Animal.class, new AnimalPropertyEditor());

        // 付类型、子类型均可匹配上对应的编辑器
        PropertyEditor customEditor1 = propertyEditorRegistry.findCustomEditor(Cat.class, null);
        PropertyEditor customEditor2 = propertyEditorRegistry.findCustomEditor(Animal.class, null);
        System.out.println(customEditor1 == customEditor2);
        System.out.println(customEditor1.getClass().getSimpleName());
    }

    /**
     * 现在的需求场景是：
     *
     * UUID类型统一交给UUIDEditor处理（当然包括Cat里面的UUID类型）
     * Person类里面的Cat的UUID类型，需要单独特殊处理，因此格式不一样需要“特殊照顾”
     *
     * 疑惑的是：实际的生产场景中那一处可以这么调用呢
     */
    @Test
    public void test6() {
        PropertyEditorRegistry propertyEditorRegistry = new PropertyEditorRegistrySupport();
        // 通用的
        propertyEditorRegistry.registerCustomEditor(UUID.class, new UUIDEditor());
        // 专用的
        propertyEditorRegistry.registerCustomEditor(Person.class, "cat.uuid", new PersonCatUUIDEditor());


        String uuidStr = "1-2-3-4-5";
        String personCatUuidStr = "1-2-3-4-5_YourBatman";

        PropertyEditor customEditor = propertyEditorRegistry.findCustomEditor(UUID.class, null);
        // customEditor.setAsText(personCatUuidStr); // 抛异常：java.lang.NumberFormatException: For input string: "5_YourBatman"
        customEditor.setAsText(uuidStr);
        System.out.println(customEditor.getAsText());

        customEditor = propertyEditorRegistry.findCustomEditor(Person.class, "cat.uuid");
        customEditor.setAsText(personCatUuidStr);
        System.out.println(customEditor.getAsText());
    }
}
