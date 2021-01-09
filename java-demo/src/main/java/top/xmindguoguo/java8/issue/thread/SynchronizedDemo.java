package top.xmindguoguo.java8.issue.thread;

/**
 * @ClassName: SynchronizedDemo
 * @author: 于国帅
 * @Description:
 * @Date: 2020/11/24 22:50
 * @Version: v1.0
 * 由于同一进程的多个线程共享同一片存储空间，在带来方便的同时，也带来了访问冲突这个严重的问题。Java语言提供了专门机制以解决这种冲突，有效避免了同一个数据对象被多个线程同时访问。
 * 需要明确的几个问题：
 *
 * synchronized关键字可以作为函数的修饰符，也可作为函数内的语句，也就是平时说的同步方法和同步语句块。如果 再细的分类，synchronized可作用于instance变量、object reference（对象引用）、static函数和class literals(类名称字面常量)身上。
 * 无论synchronized关键字加在方法上还是对象上，它取得的锁都是对象，而不是把一段代码或函数当作锁――而且同步方法很可能还会被其他线程的对象访问。
 * 每个对象只有一个锁（lock）与之相关联。
 * 实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制。
 */
public class SynchronizedDemo {
}
