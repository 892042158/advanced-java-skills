package top.xmindguoguo.common.utils.model;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
public class ModelUtil {

    private static Map<String, Object> model2map(Object modelBean, Class<?> modelType, boolean hasNullField) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = modelType.getDeclaredFields();
        for (Field field : fields) {
            // 判断是否为private属性
            if (field.getModifiers() == 2) {
                String name = field.getName();
//                ColumnName columnName = field.getAnnotation(ColumnName.class);
//                if (columnName != null) {// 注释名优先
//                    name = columnName.value();
//                }
                try {
                    field.setAccessible(true);
                    Object value = field.get(modelBean);
                    if (hasNullField || value != null) {
                        map.put(name, value);
                    }
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }
        return map;
    }

    /**
     * 过滤model内容（转义html标签）
     *
     * @param obj
     * @return T
     * @Title escapeHtml
     * @author 吕凯
     * @date 2016年11月2日 下午5:41:18
     */
    public static <T> T escapeHtml(T obj) {
        try {
            Class<?> objClass = obj.getClass();
            Map<String, Field> fieldMap = ModelUtil.getFieldMap(objClass);
            for (String key : fieldMap.keySet()) {
                Field field = fieldMap.get(key.toLowerCase());
                if (field != null) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (value != null) {
                        try {
                            if (field.getType().equals(String.class)) {// 过滤html标签
//                                NoFilterHtmlTag noFilterHtmlTag = field.getAnnotation(NoFilterHtmlTag.class);
//                                if (noFilterHtmlTag == null || !noFilterHtmlTag.value()) {
//                                    value = StringUtil.escapeHtml(StringEscapeUtils.unescapeHtml((value.toString())));
//                                }
                            }
                            // 对应使用string2Date标签的字段，将获取的String值转换为Date值放到field中(张和祥2016.09.08)
                            if (field.getType().equals(Date.class)) {
//                                String2Date string2DateTag = field.getAnnotation(String2Date.class);
//                                if (string2DateTag != null) {
//                                    field.set(obj,
//                                            ClassUtil.obj2T(DateUtil.str2Date(value.toString(), string2DateTag.value()), field.getType()));
//                                    continue;
//                                }
                            }
                            field.set(obj, ClassUtil.obj2T(value, field.getType()));
                        } catch (Exception e) {
                            log.warn("form转换出错：fieldName:“" + key + "”，fieldValue:“" + value + "”");
                        }
                    }
                }
            }
            return obj;
        } catch (Exception e) {
            log.error("过滤model发生错误", e);
            return null;
        }
    }

    private static Map<String, Field> getThisFieldMap(Class<?> modelType, boolean fieldNameFirst) {
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        Field[] fields = modelType.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (!fieldNameFirst) { // 为false时则注解名优先
//                ColumnName columnName = field.getAnnotation(ColumnName.class);
//                if (columnName != null) {// 注解名优先
//                    name = columnName.value();
//                }
            }
            fieldMap.put(name.toLowerCase(), field);
        }

        return fieldMap;
    }

    // 获取所有父类列表，直到Object类（不含Object类）
    private static List<Class<?>> getTypeList(Class<?> type) {
        List<Class<?>> classList = new LinkedList<Class<?>>();
        classList.add(type);
        Class<?> tempType = type;
        while (!(tempType = tempType.getSuperclass()).equals(Object.class)) {
            classList.add(0, tempType);
        }
        return classList;
    }

    /**
     * mdoel转成map
     *
     * @param modelBean
     * @param hasNullField 是否包含空值
     * @return Map<String   ,   Object>
     * @Title model2map
     */
    public static Map<String, Object> model2map(Object modelBean, boolean hasNullField) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Class<?>> modelTypeList = getTypeList(modelBean.getClass());
        for (Class<?> modelType : modelTypeList) {
            map.putAll(model2map(modelBean, modelType, hasNullField));
        }

        return map;
    }

    public static Map<String, Object> model2map(Object modelBean) {
        return model2map(modelBean, false);
    }

    /**
     * 获取field的Map注释名优先
     *
     * @param modelType
     * @return
     */
    public static Map<String, Field> getFieldMap(Class<?> modelType) {
        return getFieldMap(modelType, false);
    }

    /**
     * 获取field的Map
     *
     * @param modelType
     * @param fieldNameFirst field的名字优先，false为注解名优先
     * @return Map<String   ,   Field>
     * @Title getFieldMap
     * @author 吕凯
     * @date 2017年11月27日 下午2:08:26
     */
    public static Map<String, Field> getFieldMap(Class<?> modelType, boolean fieldNameFirst) {
        Map<String, Field> map = new HashMap<String, Field>();
        List<Class<?>> modelTypeList = getTypeList(modelType);
        for (Class<?> type : modelTypeList) {
            map.putAll(getThisFieldMap(type, fieldNameFirst));
        }
        return map;
    }

    /**
     * 复制model
     *
     * @param fromModel
     * @param toModel   void
     * @Title coptyModel
     */
    public static void coptyModel(Object fromModel, Object toModel) {
        Map<String, Field> fromModelMap = getFieldMap(fromModel.getClass());
        Map<String, Field> toModelMap = getFieldMap(toModel.getClass());
        for (Entry<String, Field> e : toModelMap.entrySet()) {
            Field fromField = fromModelMap.get(e.getKey());
            // 只有复制私有变量
            if (fromField != null && fromField.getModifiers() == 2) {
                try {
                    fromField.setAccessible(true);
                    Object value = fromField.get(fromModel);
                    Field field = e.getValue();
                    if (value != null && value.getClass().equals(field.getType())) {
                        field.setAccessible(true);
                        field.set(toModel, value);

                    }
                } catch (Exception e1) {
                    log.error("", e1);
                }
            }
        }
    }

    /**
     * map类型转成model
     *
     * @param map       map数值
     * @param modelType model类型
     * @return T
     * @Title map2model
     */
    public static <T> T map2model(Map<String, Object> map, Class<T> modelType) {
        try {
            Map<String, Object> newMap = new HashMap<String, Object>();
            for (String key : map.keySet()) {
                Object value = map.get(key);
                if (key != null && value != null) {
                    newMap.put(key.toLowerCase(), value);
                }
            }

            T modelBean = modelType.newInstance();
            Map<String, Field> fieldMap = getFieldMap(modelType);
            for (Field field : fieldMap.values()) {
                // 判断是否为private属性
                if (field.getModifiers() == 2) {
                    String name = field.getName();
//                    ColumnName columnName = field.getAnnotation(ColumnName.class);
//                    if (columnName != null) {
//                        name = columnName.value();
//                    }
                    field.setAccessible(true);
                    Object value = newMap.get(name.toLowerCase());
                    try {
//                        if (field.getType().equals(Date.class) && value instanceof String) {
//                            // 如果model中字段是Date类型，而map中对应值是String时，需要把String转换为Date
//                            String strValue = value == null ? "" : value.toString();
//                            if (strValue.indexOf("/") > -1) {
//                                value = DateUtil.str2Date(strValue, "yyyy/MM/dd");
//                            } else {
//                                value = DateUtil.str2Date(strValue);
//                            }
//                        } else {
                        value = ClassUtil.obj2T(value, field.getType());
//                        }

                        field.set(modelBean, value);
                    } catch (Exception e) {
                        log.error("字段设置错误，字段名：“" + name + "”，字段值：“" + value + "”，" + e.getMessage());
                    }
                }
            }
            return modelBean;
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 转换所有属性为null且为String类型==》""
     *
     * @param modelBean 实体bean void
     * @Title confirmValues
     */
    public static void confirmValues(Object modelBean) {
        Map<String, Field> fieldMap = getFieldMap(modelBean.getClass());
        for (Field field : fieldMap.values()) {
            // 判断是否为private属性
            if (field.getModifiers() == 2) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(modelBean);
                    if (value == null) {
                        if (field.getType().equals(String.class)) {
                            field.set(modelBean, "");
                        }
                    }
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }
    }

    /**
     * model对象中是否存在属性fieldName
     *
     * @param model
     * @param fieldName
     * @return boolean
     * @Title hasField
     * @author 吕凯
     * @date 2018年6月26日 下午4:59:44
     */
    public static boolean hasField(Object model, String fieldName) {
        try {
            Field field = getField(model.getClass(), fieldName);
            return field != null;
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return false;
    }

    public static Field getField(Class<?> clazz, String name) {
        if (clazz == null || clazz.equals(Object.class)) {
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
//            ColumnName columnName = field.getAnnotation(ColumnName.class);
//            if (columnName != null) {
//                fieldName = columnName.value();
//            }
            if (fieldName.equals(name)) {
                return field;
            }
        }
        return getField(clazz.getSuperclass(), name);
    }

    /**
     * 获取model的某个属性值
     *
     * @param model     实体model
     * @param fieldName 字段值
     * @return Object
     * @Title getModelValue
     */
    public static Object getModelValue(Object model, String fieldName) {
        try {
            Field field = getField(model.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                return field.get(model);
            }
            log.info("getModelValue属性未找到：" + fieldName + " class:" + model.getClass());
        } catch (Exception e) {
            log.error(model + "中找不到" + fieldName + "属性", e);
            log.warn(e.getMessage());
        }
        return null;
    }

    /**
     * 给model的某个属性赋值
     *
     * @param model     实体model
     * @param fieldName 字段
     * @param value     字段值
     * @return boolean
     * @Title setModelValue
     */
    public static boolean setModelValue(Object model, String fieldName, Object value) {
        try {
            Field field = getField(model.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                if (value != null && !field.getType().equals(value.getClass())) {
                    value = ClassUtil.obj2T(value, field.getType());
                }
                field.set(model, value);
                return true;
            }
            log.error("setModelValue属性未找到", new Exception());
        } catch (Exception e) {
            log.error("设置" + model + "中属性" + fieldName + "出错", e);
        }
        return false;
    }

    public static <T> Object getModelId(T modelBean) {
        return getModelValue(modelBean, "id");
    }
}
