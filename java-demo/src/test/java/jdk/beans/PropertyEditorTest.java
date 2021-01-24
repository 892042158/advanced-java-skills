package jdk.beans;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 22:57
 * @Version: v1.0
 * PropertyEditor设计缺陷
 * 前提说明：本文指出它的设计缺陷，只讨论把它当做类型转换器在转换场景下存在的一些缺陷。
 *
 * 职责不单一：该接口有非常多的方法，但只用到2个而已
 * 类型不安全：setValue()方法入参是Object，getValue()返回值是Object，依赖于约定好的类型强转，不安全
 * 线程不安全：依赖于setValue()后getValue()，实例是线程不安全的
 * 语义不清晰：从语义上根本不能知道它是用于类型转换的组件
 * 只能用于String类型：它只能进行String <-> 其它类型的转换，而非更灵活的Object <-> Object
 */
public class PropertyEditorTest {
}
