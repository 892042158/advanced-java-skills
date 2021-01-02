package jdk.lang;

import java.io.Console;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class SystemTest {
    // 复制数组 直接调用底层的方法，效率最高，当时对象时，只是浅复制
    /**
     * System.arraycopy public static void native arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
     * 
     * @参数： src - 源数组。
     * 
     * srcPos - 源数组中的起始位置。
     * 
     * dest - 目标数组。
     * 
     * destPos - 目标数据中的起始位置。
     * 
     * length - 要复制的数组元素的数量
     * 
     * @Title arraycopy
     * @author 于国帅
     * @date 2019年1月19日 下午7:42:44 void
     */
    @Test
    public void arraycopy() {

        String[] src = { "0", "1", "2", "3", "4", "5" }; // 需要复制进来的数组
        String[] dest = new String[src.length]; // 需要复制进来的数组
        // 执行从 从2 到4 复制到dest 数组中
        System.arraycopy(src, 2, dest, 0, 2);
        System.err.println(StringUtils.join(dest, ",")); // 2,3,,,,
    }

    // 可以参考这个类，把能够获取的系统自带的数据都带了
    // org.apache.commons.lang.SystemUtils
    // SystemUtilsTest
    // 打印jdk的路径
    @Test
    public void getProperty() {

        System.err.println(System.getProperty("java.home"));
//        java.version Java ：运行时环境版本
//        java.vendor Java ：运行时环境供应商
//        java.vendor.url ：Java供应商的 URL
//        java.home &nbsp;&nbsp;：Java安装目录
//        java.vm.specification.version： Java虚拟机规范版本
//        java.vm.specification.vendor ：Java虚拟机规范供应商
//        java.vm.specification.name &nbsp; ：Java虚拟机规范名称
//        java.vm.version ：Java虚拟机实现版本
//        java.vm.vendor ：Java虚拟机实现供应商
//        java.vm.name&nbsp; ：Java虚拟机实现名称
//        java.specification.version：Java运行时环境规范版本
//        java.specification.vendor：Java运行时环境规范供应商
//        java.specification.name ：Java运行时环境规范名称
//        java.class.version ：Java类格式版本号
//        java.class.path ：Java类路径
//        java.library.path  ：加载库时搜索的路径列表
//        java.io.tmpdir  ：默认的临时文件路径
//        java.compiler  ：要使用的 JIT编译器的名称
//        java.ext.dirs ：一个或多个扩展目录的路径
//        os.name ：操作系统的名称
//        os.arch  ：操作系统的架构
//        os.version  ：操作系统的版本
//        file.separator ：文件分隔符
//        path.separator ：路径分隔符
//        line.separator ：行分隔符
//        user.name ：用户的账户名称
//        user.home ：用户的主目录
//        user.dir：用户的当前工作目录
    }

    // System.setProperty 相当于一个静态变量 ，存在内存里面！
    @Test
    public void setProperty() {
        System.setProperty("test", "测试在哪里");
        System.err.println(System.getProperty("test"));
    }

    // 从内存中清除设置的属性
    @Test
    public void clearProperty() {

        System.setProperty("test", "测试在哪里"); // System.setProperty 相当于一个静态变量 ，存在内存里面！
        System.err.println(System.getProperty("test"));
        System.clearProperty("test");
        System.err.println(System.getProperty("test"));
        // 测试系统变量是否能够从内存中清除，也是可以的
        System.err.println(System.getProperty("java.home"));
        System.clearProperty("java.home");
        System.err.println(System.getProperty("java.home"));

    }

    // 访问与当前Java虚拟机关联的基于字符的控制台设备（如果有的话）的方法。
    // 虚拟机是否具有控制台取决于底层平台以及虚拟机的调用方式。
    // 如果虚拟机从交互式命令行启动，而不重定向标准输入和输出流，则其控制台将存在，并且通常将连接到启动虚拟机的键盘和显示器。 如果虚拟机是自动启动的，例如由后台作业调度程序启动，则通常不具有控制台。
    Console console = System.console(); // 感觉通过这句话能够判断是不是定时任务啊

    @Test
    public void console() {

        // 这就是为什么这段代码只能在控制台中执行，而不能在eclipse中运行。
        // 安全注意事项：如果应用程序需要读取密码或其他安全数据，则应使用readPassword()或readPassword(String, Object...) ，并在处理后手动归零返回的字符数组，以最小化内存中敏感数据的生命周期。

    }

    // 回当前时间（以毫秒为单位）。 请注意，虽然返回值的时间单位为毫秒，但该值的粒度取决于底层操作系统，并且可能较大。 例如，许多操作系统以几十毫秒为单位测量时间。
    @Test
    public void currentTimeMillis() {
        System.err.println(System.currentTimeMillis());
    }

    // 以纳秒为单位返回正在运行的Java虚拟机的高分辨率时间源的当前值。
    @Test
    public void nanoTime() {
        System.err.println(System.currentTimeMillis());
    }

    // 终止当前运行的Java虚拟机 等效于 Runtime.getRuntime().exit(n)
    @Test
    public void exit() {
        System.err.println("终止虚拟机开始 0代表终止，其他数字代表异常终止");
        System.exit(0);
    }

    // 清理当前运行的虚拟机的内存，不会立即清理， 只是告诉虚拟机你该清理了
    @Test
    public void gc() {
        System.gc();
    }

    // 方法是获取指定的环境变量的值
    @Test
    public void getenv() {
//        USERPROFILE        ：用户目录
//        USERDNSDOMAIN      ：用户域
//        PATHEXT            ：可执行后缀
//        JAVA_HOME          ：Java安装目录
//        TEMP               ：用户临时文件目录
//        SystemDrive        ：系统盘符
//        ProgramFiles       ：默认程序目录
//        USERDOMAIN         ：帐户的域的名称
//        ALLUSERSPROFILE    ：用户公共目录
//        SESSIONNAME        ：Session名称
//        TMP                ：临时目录
//        Path               ：path环境变量
//        CLASSPATH          ：classpath环境变量
//        PROCESSOR_ARCHITECTURE ：处理器体系结构
//        OS                     ：操作系统类型
//        PROCESSOR_LEVEL    ：处理级别
//        COMPUTERNAME       ：计算机名
//        Windir             ：系统安装目录
//        SystemRoot         ：系统启动目录
//        USERNAME           ：用户名
//        ComSpec            ：命令行解释器可执行程序的准确路径
//        APPDATA            ：应用程序数据目录
        Map<String, String> map = System.getenv();
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            System.out.println(key + "=" + map.get(key));
        }
    }

    @Test
    public void getenv(String name) {

    }

    // 获得Properties ，后面学到这个类，在进行深入
    @Test
    public void getProperties() {
        Properties prop = System.getProperties();
        // 等过debug读取
        // 就是 System.getProperty 获取的单个 值

    }

    // 获得系统安全管理器 ，后面学到这个类，在进行深入
    @Test
    public void getSecurityManager() {
        SecurityManager sm = System.getSecurityManager();
    }

    // 返回与默认方法hashCode（）返回的给定对象相同的哈希码，无论给定对象的类是否覆盖了hashCode（）。
    // 单元测试不能够运行的时候，请放到main方法里面执行
    @Test
    public void identityHashCode() {
        // 这个方法很管用，在对象重写hashCode时仍然能够返回原先的hash
        System.err.println(System.identityHashCode("222"));
        HashModel hashModel = new HashModel(); // 当不重写的时候
        System.err.println(System.identityHashCode(hashModel)); // 705927765
        System.err.println(hashModel.hashCode()); // 705927765
        // 重写的时候
        System.err.println(System.identityHashCode(hashModel)); // 705927765
        System.err.println(hashModel.hashCode()); // 31

    }

    @Test
    public void lineSeparator() {

        System.err.println(System.lineSeparator()); //
    }

//    java中的finalize()方法
//@see https://blog.csdn.net/nyistzp/article/details/12253599
//    当垃圾收集器认为没有指向对象实例的引用时，会在销毁该对象之前调用finalize()方法。该方法最常见的作用是确保释放实例占用的全部资源。java并不保证定时为对象实例调用该方法，甚至不保证方法会被调用，所以该方法不应该用于正常内存处理。
//   
    @Test
    public void runFinalization() {
        System.runFinalization(); // 强制调用已经失去引用的对象的finalize方法
    }

    public static void main(String[] args) {
        System.err.println(System.lineSeparator());

    }
    // 剩余的方法 等到学习到这几个类，再回来补充 避免学习太深忘记自己的最初目标
//    static void setErr(PrintStream err) 
//    重新分配“标准”错误输出流。  
//    static void setIn(InputStream in) 
//    重新分配“标准”输入流。  
//    static void setOut(PrintStream out) 
//    重新分配“标准”输出流。  
//    static void setProperties(Properties props) 
//    将系统属性设置为 Properties参数。  
//    static String setProperty(String key, String value) 
//    设置由指定键指示的系统属性。  
//    static void setSecurityManager(SecurityManager s) 
//    设置系统安全性。  

}

class HashModel {
    private Long id;

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        HashModel other = (HashModel) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;
//        return true;
//    }

}
