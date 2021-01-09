package top.xmindguoguo.java8.issue;

/**
 * 想要测试泛型父类 子类继承方法是否都能够使用泛型
 * 
 * @ClassName GenericityTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月23日 上午11:48:58
 *
 */
public class GenericityTest {
    public static void main(String[] args) {

        Child c = new Child();
        System.err.println(c.getClassName());
    }
}

class Parent<T> {
    private String className;

    public Parent() {
        this.className = this.getClass().getGenericSuperclass().toString();
    }

    public String getClassName() {
        return className;
    }

//    public T getT() {
//    }

}

class Child extends Parent<GenericityTest> {

}
