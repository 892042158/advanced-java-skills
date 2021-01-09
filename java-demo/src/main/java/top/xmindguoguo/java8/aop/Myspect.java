package top.xmindguoguo.java8.aop;

/**
 * 代理类
 * 
 * @ClassName Myspect
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年8月21日 上午10:50:14
 *
 */
public class Myspect {
    public void befor() {
        System.out.println("aop  befor");
    }

    public void after() {
        System.out.println("aop  after");
    }
}
