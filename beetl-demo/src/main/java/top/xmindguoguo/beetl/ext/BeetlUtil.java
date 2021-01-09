package top.xmindguoguo.beetl.ext;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板引擎工具类
 * 
 * @author kevin
 * 
 */
public final class BeetlUtil {

    private BeetlUtil() {
        super();
    }

    private static StringTemplateResourceLoader STRING_RESOURCE_LOADER = new StringTemplateResourceLoader(); // 字符串模板

    private static Configuration cfg = null;
    private static GroupTemplate gt = null;
    private static GroupTemplate STRING_GT = null; // 字符串模板

    static {
        try {
            cfg = Configuration.defaultConfiguration();
            STRING_GT = new GroupTemplate(STRING_RESOURCE_LOADER, cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setSharedVars(String key, Object value) {
        Map<String, Object> shared = gt.getSharedVars();
        if (shared == null) {
            shared = new HashMap<String, Object>();
        }
        shared.put(key, value);
        gt.setSharedVars(shared);
    }

    public static void setSharedVars(Map<String, Object> shared) {
        Map<String, Object> sharedExist = gt.getSharedVars();
        if (sharedExist == null) {
            sharedExist = new HashMap<String, Object>();
        }
        sharedExist.putAll(shared);
        gt.getSharedVars().putAll(sharedExist);
    }

    /**
     * 将字符串与模板变量组装 //将带有占位符的sql替换为变量
     * 
     * @Title getString
     * @author 于国帅
     * @date 2018年6月12日 下午12:49:34
     * @param str
     * @param map
     * @return String
     */
    public static String getString(String str, Map<String, Object> map) {
        Template t = STRING_GT.getTemplate(str);
        t.binding(map);
        return t.render();
    }


}
