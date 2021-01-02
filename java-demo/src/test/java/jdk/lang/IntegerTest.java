package jdk.lang;

public class IntegerTest {
    /**
     * 改变缓存大小
     * 
     * @see https://blog.csdn.net/qq_36791569/article/details/80438292
     * @Title test
     * @author 于国帅
     * @date 2019年1月16日 上午11:47:41 void
     */
    public static void main(String[] args) {
        // -XX:AutoBoxCacheMax=1000运行时配置
        Integer.valueOf(1);

        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);

        Integer e = 1000;
        Integer f = 1000;
        System.out.println(e == f);

        Integer g = 1001;
        Integer h = 1001;
        System.out.println(g == h);

        Integer i = 20000;
        Integer j = 20000;
        System.out.println(i == j);
    }
}
