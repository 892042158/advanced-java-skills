package top.xmindguoguo.quartz.util;

import lombok.extern.slf4j.Slf4j;
import top.xmindguoguo.common.utils.model.ClassUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ScanScheduledUtil {

    private static String defaultScheduledPackage = "com.ld.timetask"; //

    /**
     * 加载Scheduled模块
     * 
     * @Title loadScheduledModel
     * @author 刘金浩
     * @date 2018年1月30日 下午4:24:50 void
     */
    public static List<Map<String, Class<?>>> loadScheduledModel() {
        return loadScheduledModel(defaultScheduledPackage);
    }

    /**
     * 加载Scheduled模块
     * 
     * @Title loadScheduledModel
     * @author 刘金浩
     * @date 2018年1月30日 下午4:24:50 void
     */
    public static List<Map<String, Class<?>>> loadScheduledModel(String defaultPackageName) {
        if (defaultPackageName.length() == 0)
            defaultPackageName = defaultScheduledPackage;
        List<Map<String, Class<?>>> list = new ArrayList<Map<String, Class<?>>>();
        if (defaultPackageName != null && defaultPackageName.length() > 0) {
            String[] defaultPackageNameArrs = defaultPackageName.split(",");
            for (int i = 0; i < defaultPackageNameArrs.length; i++) {
                String packageName = defaultPackageNameArrs[i];
                List<Class<? extends BaseJob>> actionClassList = ClassUtil.getClassList(packageName, BaseJob.class, true); // 加载jar包中的action（需测试不同项目的兼容性）
                for (Class<? extends BaseJob> actionClass : actionClassList) {
                    ScheduledName actionName = actionClass.getAnnotation(ScheduledName.class);
                    if (actionName != null) {
                        Map<String, Class<?>> map = new HashMap<String, Class<?>>();
                        map.put(actionName.id(), actionClass);
                        list.add(map);
                    }
                }
            }
        }
        log.warn("========共加载Scheduled路由处理器  " + list.size() + "个========" + list);
        return list;
    }

    public static void main(String[] args) {
        loadScheduledModel("");
    }
}
