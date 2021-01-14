package org.springframework.lang;

/**
 * 在写程序的时候你可以定义是否可为空指针。通过使用像@NotNull和@Nullable之类的annotation来声明一个方法是否是空指针安全的。现代的编译器、IDE或者工具可以读此annotation并帮你添加忘记的空指针检查，或者向你提示出不必要的乱七八糟的空指针检查。IntelliJ和findbugs已经支持了这些annotation。这些annotation同样是JSR
 * 305的一部分，但即便IDE或工具中没有，这个annotation本身可以作为文档。看到@NotNull和@Nullable，程序员自己可以决定是否做空指针检查。顺便说一句，这个技巧对Java程序员来说相对比较新，要采用需要一段时间。
 * --------------------- 作者：ashencode 来源：CSDN 原文：https://blog.csdn.net/ashencode/article/details/81865462 版权声明：本文为博主原创文章，转载请附上博文链接！
 * 
 * @ClassName NullableTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年3月5日 下午5:28:59
 *
 */
public class NullableTest {

}
