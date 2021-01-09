package top.xmindguoguo.java8.aop;

public class TestAop {
    public static void main(String[] args) {
        // 测试手写的aop
        UserService user = new UserServiceImpl();
        UserService proxyUser = (UserService) ProxyFactory.createProxy(user);
        proxyUser.add();

    }
}
