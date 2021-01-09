package top.xmindguoguo.java8.jvmtest;

/**
 * 
 * vm Args -Xss128k
 * 
 * @ClassName JavaVMStackSOF
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年5月11日 下午3:30:30
 *
 */
public class JavaVMStackSOF {
    private int stackLen = 1;

    public void stackLeak() {
        stackLen++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Exception e) {
            System.err.println(sof.stackLen);
            System.err.println(e);
        }
    }
}
