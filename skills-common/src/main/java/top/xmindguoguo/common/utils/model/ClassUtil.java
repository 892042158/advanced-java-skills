package top.xmindguoguo.common.utils.model;

import lombok.extern.slf4j.Slf4j;
import top.xmindguoguo.common.utils.file.PathUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 
 * 数据转换操作相关
 * 
 * @ClassName Class
 * Util
 * @author <a href="mailto:donggongai@126.com" target="_blank">kevin</a>
 * @date 2016年8月25日 下午2:59:02
 *
 */
@Slf4j
public class ClassUtil {
    private final static String ROOT_PATH = PathUtil.getProjectAbsolutePath();

    @SuppressWarnings("unchecked")
    private static <T> void addClassName(List<Class<? extends T>> clazzList, String clazzName, Class<T> superClass) {
        if (clazzList != null && clazzName != null) {
            clazzName = clazzName.replace(".class", "");
            Class<?> clazz = null;
            try {
                clazz = Class.forName(clazzName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            // 不包含父类
            if (clazz != null && superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)) {
                clazzList.add((Class<T>) clazz);
            }
        }
    }

    /**
     * 
     * Date转换成int
     * 
     * @Title date2int
     * @param dateStr
     *            date字符串
     * @return int
     */
    public static int date2int(String dateStr) {
        int dateInt;
        try {
            dateInt = Integer.parseInt(dateStr.replace("-", ""));
        } catch (Exception e) {
            log.warn("日期转换出错：'" + dateStr + "'");
            dateInt = 0;
        }
        return dateInt;
    }

    /**
     * 
     * Date转换成Long
     * 
     * @Title date2long
     * @param dateStr
     *            date字符串
     * @return long
     */
    public static long date2long(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            log.warn("日期转换出错：'" + dateStr + "'");
            return 0;
        }
    }

    private static File[] filterClassFiles(String pkgPath) {
        if (pkgPath == null) {
            return null;
        }
        // 接收 .class 文件 或 类文件夹
        return new File(pkgPath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }

        });
    }

    /**
     * 本地文件
     * 
     * @Title findClassName
     * @author 吕凯
     * @date 2016年10月11日 上午8:43:48
     * @param clazzList
     * @param pkgName
     * @param pkgPath
     * @param isRecursive
     * @param superClass
     *            void
     */
    public static <T> void findClassName(List<Class<? extends T>> clazzList, String pkgName, String pkgPath, boolean isRecursive,
            Class<T> superClass) {
        if (clazzList == null) {
            return;
        }
        File[] files = filterClassFiles(pkgPath);// 过滤出.class文件及文件夹
        if (files != null) {
            for (File f : files) {
                String fileName = f.getName();
                if (f.isFile()) {
                    // .class 文件的情况
                    String clazzName = getClassName(pkgName, fileName);
                    addClassName(clazzList, clazzName, superClass);
                } else {
                    // 文件夹的情况
                    if (isRecursive) {
                        // 需要继续查找该文件夹/包名下的类
                        String subPkgName = pkgName + "." + fileName;
                        String subPkgPath = pkgPath + "/" + fileName;
                        findClassName(clazzList, subPkgName, subPkgPath, true, superClass);
                    }
                }
            }
        }
    }

    /**
     * 第三方Jar类库的引用。<br/>
     * 
     * @throws IOException
     */
    public static <T> void findClassName(List<Class<? extends T>> clazzList, String pkgName, URL url, boolean isRecursive,
            Class<T> superClass) throws IOException {
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
        JarFile jarFile = jarURLConnection.getJarFile();
        log.warn("获取jar包中" + superClass.getName() + "的子类:" + pkgName + " jarFile:" + jarFile.getName());
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName(); // 类似：sun/security/internal/interfaces/TlsMasterSecret.class
            String clazzName = jarEntryName.replace("/", ".");
            int endIndex = clazzName.lastIndexOf(".");
            String prefix = null;
            if (endIndex > 0) {
                String prefix_name = clazzName.substring(0, endIndex);
                endIndex = prefix_name.lastIndexOf(".");
                if (endIndex > 0) {
                    prefix = prefix_name.substring(0, endIndex);
                }
            }
            if (prefix != null && jarEntryName.endsWith(".class")) {
                if (prefix.equals(pkgName)) {
                    log.info("jar entryName:" + jarEntryName);
                    addClassName(clazzList, clazzName, superClass);
                } else if (isRecursive && prefix.startsWith(pkgName)) {
                    // 遍历子包名：子类
                    log.info("jar entryName:" + jarEntryName + " isRecursive:" + isRecursive);
                    addClassName(clazzList, clazzName, superClass);
                }
            }
        }
    }

    /**
     * 
     * 获取某个包下面的所有类名
     * 
     * @Title getClassList
     * @param pckgname
     * @param superClass
     * @return List<Class<? extends T>>
     */
    @SuppressWarnings("unchecked")
    public final static <T> List<Class<? extends T>> getClassList(String pckgname, Class<T> superClass) {
        List<Class<? extends T>> classes = new ArrayList<>();
        File directory = new File(ROOT_PATH + pckgname.replace('.', '/'));
        if (directory.exists()) {
            File[] files = directory.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (fileName.endsWith(".class")) {
                    try {
                        Class<?> cl = Class.forName(pckgname + '.' + fileName.replace(".class", ""));
                        if (superClass.isAssignableFrom(cl)) {
                            classes.add((Class<T>) cl);
                        }
                    } catch (ClassNotFoundException e) {
                        log.error("", e);
                    }
                } else if (files[i].isDirectory()) {
                    classes.addAll(getClassList(pckgname + "." + fileName, superClass));
                }
            }
        }
        return classes;
    }

    /**
     * 获取某个包下面的所有父类为superClass的类名（jar包下的也会获取）
     * 
     * @Title getClassList
     * @author 吕凯
     * @date 2016年10月10日 下午5:02:43
     * @param pkgName
     *            包名
     * @param superClass
     *            父类
     * @param isRecursive
     *            是否查询下级子目录
     * @return List<Class<?>>
     */
    public static <T> List<Class<? extends T>> getClassList(String pkgName, Class<T> superClass, boolean isRecursive) {
        List<Class<? extends T>> classList = new ArrayList<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            // 按文件的形式去查找
            String strFile = pkgName.replaceAll("\\.", "/");
            Enumeration<URL> urls = loader.getResources(strFile);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    String pkgPath = url.getPath();
                    log.warn("protocol:" + protocol + " path:" + pkgPath + " 父类：" + superClass.getName());
                    if ("file".equals(protocol)) {
                        // 本地自己可见的代码
                        findClassName(classList, pkgName, pkgPath, isRecursive, superClass);
                    } else if ("jar".equals(protocol)) {
                        // 引用第三方jar的代码
                        findClassName(classList, pkgName, url, isRecursive, superClass);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classList;
    }

    private static String getClassName(String pkgName, String fileName) {
        int endIndex = fileName.lastIndexOf(".");
        String clazz = null;
        if (endIndex >= 0) {
            clazz = fileName.substring(0, endIndex);
        }
        String clazzName = null;
        if (clazz != null) {
            clazzName = pkgName + "." + clazz;
        }
        return clazzName;
    }

    public static void main(String[] args) {
        // 标识是否要遍历该包路径下子包的类名
//      boolean recursive = false;  
        boolean recursive = true;
        // 指定的包名
//      String pkg = "javax.crypto.spec";// 为java/jre6/lib/jce.jar，普通的java工程默认已引用  
//        String pkg = "com.ld";
        String pkg = "com.sun.jna.win32";
        List<?> list = null;
//      list = getClassList(pkg, recursive, null);  
        // 增加 author.class的过滤项，即可只选出ClassTestDemo
        list = getClassList(pkg, Object.class, recursive);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ":" + list.get(i));
        }
        System.exit(0);
    }

    /**
     * 
     * Object转换成Double类型
     * 
     * @Title obj2doulbe
     * @param obj
     *            Object
     * @return double
     */
    public static double obj2doulbe(Object obj) {
        if (obj != null) {
            try {
                return Double.parseDouble(obj.toString());
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    /**
     * 
     * Object转换成Int类型
     * 
     * @Title obj2int
     * @param obj
     *            Object
     * @return int
     */
    public static int obj2int(Object obj) {
        if (obj != null) {
            try {
                return Integer.parseInt(obj.toString());
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    /**
     * 
     * Object转换成Long类型
     * 
     * @Title obj2long
     * @param obj
     *            Object
     * @return long
     */
    public static long obj2long(Object obj) {
        if (obj != null) {
            try {
                return Long.parseLong(obj.toString());
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static <T> T obj2T(Object obj, Class<T> type) {
        if (obj == null) {
            return null;
        }
        Object model = null;
        if (type.equals(Integer.class) || type.equals(int.class)) {
            model = (obj.equals("")) ? null : new Integer(obj.toString());
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            model = (obj.equals("")) ? null : new Long(obj.toString());
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            model = (obj.equals("")) ? null : new Float(obj.toString());
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            model = (obj.equals("")) ? null : new Double(obj.toString());
        } else if (type.equals(String.class)) {
            model = (obj == null) ? null : obj.toString();
        } else {
            model = obj;
        }
        return (T) model;
    }
}
