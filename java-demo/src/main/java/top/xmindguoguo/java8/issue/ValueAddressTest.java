package top.xmindguoguo.java8.issue;

public class ValueAddressTest {

    public static void main(String[] args) {
        ValueAddressTest t = new ValueAddressTest();
        MyObj obj = new MyObj();
        t.test2(obj);// 这里传递的参数obj就是引用传递
//            System.out.println(obj.b);
    }

    public void test2(MyObj obj) {
        obj = new MyObj(); // 纠结在这一行
        obj.b = 100;
    }
}

class MyObj {
    public int b = 99;
}
