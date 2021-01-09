package lang;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.junit.Test;

public class SystemUtilsTest {
    @Test
    public void SystemUtils() {
        System.out.println(genHeader("SystemUtilsDemo"));
        System.out.println("获得userhome.用户路径");
        System.out.println(SystemUtils.USER_HOME);
        System.out.println("获得userdir项目路径.");
        System.out.println(SystemUtils.USER_DIR);
        System.out.println("获得user.");
        System.out.println(SystemUtils.USER_NAME);
        System.out.println("获得USER_TIMEZONE.");
        System.out.println(SystemUtils.USER_TIMEZONE);
        System.out.println("获得系统文件分隔符.");
        System.out.println(SystemUtils.FILE_SEPARATOR);

        System.out.println("获得源文件编码.");
        System.out.println(SystemUtils.FILE_ENCODING);

        System.out.println("获得ext目录.");
        System.out.println(SystemUtils.JAVA_EXT_DIRS);

        System.out.println("获得java版本.");
        System.out.println(SystemUtils.JAVA_VM_VERSION);

        System.out.println("获得java厂商.");
        System.out.println(SystemUtils.JAVA_VENDOR);
    }

    private String genHeader(String head) {
        String[] header = new String[3];
        header[0] = StringUtils.repeat("*", 50);
        header[1] = StringUtils.center("  " + head + "  ", 50, "^O^");
        header[2] = header[0];
        return StringUtils.join(header, "\n");
    }
}
