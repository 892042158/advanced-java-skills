package top.xmindguoguo.java8.aop;

public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println(this.getClass().getSimpleName() + "jdk");
    }

}
