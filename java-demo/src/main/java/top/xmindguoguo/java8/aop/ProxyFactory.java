package top.xmindguoguo.java8.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 工厂
 * 
 * @ClassName ProxyFactory
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年8月21日 上午10:50:02
 *
 */
public class ProxyFactory {
    // 增强代码的类
    final static Myspect ms = new Myspect();

    // 将需要申请代理的对象传递过来，然后会创建一个此对象的增强类
    public static Object createProxy(Object us) {
        /**
         * 第一个参数 ： 类加载器 第二个参数： 该对象所实现的接口 第三个参数： 增强方法的执行者
         */
        ClassLoader usClassLoader = us.getClass().getClassLoader();
        Class<?>[] usInterFace = us.getClass().getInterfaces();
        Object proxyObj = Proxy.newProxyInstance(usClassLoader, usInterFace, new InvocationHandler() {
            /**
             * 每次代理对象调用方法时都会执行这个方法 proxy 代理对象 method 对象执行的方法 args 方法的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ms.befor();
                // 执行原来的方法
                Object returnObj = method.invoke(us, args);
                ms.after();
                return returnObj;
            }
        });
        return proxyObj;
    }

}
