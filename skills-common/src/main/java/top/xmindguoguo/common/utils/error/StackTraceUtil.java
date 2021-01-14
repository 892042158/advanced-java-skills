package top.xmindguoguo.common.utils.error;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * java 堆栈调用信息输出
 * 
 * @ClassName StackTraceUtil
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月10日 下午4:51:09
 *
 */
@Slf4j
public class StackTraceUtil {
    /**
     * 获取当前线程的全部调用堆栈链信息
     * 
     * @Title getStackTraceElement
     * @author 于国帅
     * @date 2019年1月10日 下午4:50:46
     * @return StackTraceElement[]
     */
    public static StackTraceElement[] getStackTraceElement() {
        return Thread.currentThread().getStackTrace();
    }

    /**
     * 只获取指定包下的堆栈
     * 
     * @Title getStackTraceElement
     * @author 于国帅
     * @date 2019年1月10日 下午4:56:07
     * @param packageName
     * @return List<StackTraceElement>
     */
    public static List<StackTraceElement> getStackTraceElement(String packageName) {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        List<StackTraceElement> stackList = new LinkedList<>();
        for (StackTraceElement ste : stack) {
            String callName = ste.getClassName();
            if (callName.startsWith(packageName) && !callName.equals(StackTraceUtil.class.getName())) {
                stackList.add(ste);
            }
        }
        return stackList;
    }

    public static void sysoStackTraceElement() {
        for (StackTraceElement ste : getStackTraceElement()) {
            // 换行输出StackTraceUtil.java
            log.error(ste.toString() + "\n");
        }
    }
}
