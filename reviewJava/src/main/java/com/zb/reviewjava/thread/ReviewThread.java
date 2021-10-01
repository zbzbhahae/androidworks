package com.zb.reviewjava.thread;


/**
 * id	线程 id 用于标识不同的线程。编号可能被后续创建的线程使用。编号是只读属性，不能修改
 * name	名字的默认值是 Thread-(id)
 * daemon	分为守护线程和用户线程，我们可以通过 setDaemon(true) 把线程设置为守护线程。守护线程通常用于执行不重要的任务，比如监控其他线程的运行情况，GC 线程就是一个守护线程。setDaemon() 要在线程启动前设置，否则 JVM 会抛出非法线程状态异常，可被继承。
 * priority	线程调度器会根据这个值来决定优先运行哪个线程（不保证），优先级的取值范围为 1~10，默认值是 5，可被继承。Thread 中定义了下面三个优先级常量：
 * - 最低优先级：MIN_PRIORITY = 1
 * - 默认优先级：NORM_PRIORITY = 5
 * - 最高优先级：MAX_PRIORITY = 10
 */
public class ReviewThread {

    public static void test() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(Thread.interrupted()) {
                        //外部调用了线程打断，需要做后续工作  进入此判断后 线程打断标识会重置
                        return;
                    }
                    //工作
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        //外部调用了线程打断，需要做后续工作  进入此判断后 线程打断标识会重置 切记 两个地方都要判断
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();//开启线程
        t.interrupt();//打断线程 stop已停用  interrupt需要在线程内部判断
    }





}
/**
 * 状态	说明
 * New	                            新创建了一个线程对象，但还没有调用start()方法。
 * Runnable	Ready           状态 线程对象创建后，其他线程(比如 main 线程）调用了该对象的 start() 方法。该状态的线程位于可运行线程池中，等待被线程调度选中 获取 cpu 的使用权。Running 绪状态的线程在获得 CPU 时间片后变为运行中状态（running）。
 * Blocked	                        线程因为某种原因放弃了cpu 使用权（等待锁），暂时停止运行
 * Waiting	                        线程进入等待状态因为以下几个方法：
 * - Object#wait()
 * - Thread#join()
 * - LockSupport#park()
 * Timed Waiting	            有等待时间的等待状态。
 * Terminated	                表示该线程已经执行完毕。
 */


/**
  *wait() / notify() / notifyAll()
 * wait()，notify()，notifyAll() 是定义在Object类的实例方法，用于控制线程状态，三个方法都必须在synchronized 同步关键字所限定的作用域中调用，否则会报错 java.lang.IllegalMonitorStateException。
 *
 * 方法	            说明
 * wait()	        线程状态由 的使用权。Running 变为 Waiting, 并将
 *                      当前线程放入等待队列中
 * notify()	        notify() 方法是将等待队列中一个等待线程从等待队列
 *                      移动到同步队列中
 * notifyAll()	    则是将所有等待队列中的线程移动到同步队列中
 *                      被移动的线程状态由 Running 变为 Blocked，
 *                      notifyAll 方法调用后，等待线程依旧不会从 wait()
 *                      返回,需要调用 notify() 或者 notifyAll() 的线程释放
 *                      掉锁后，等待线程才有机会从 wait() 返回。
 *
 * join() / sleep() / yield()
 * 在很多情况，主线程创建并启动子线程，如果子线程中需要进行
 * 大量的耗时计算，主线程往往早于子线程结束。这时，如果主线
 * 程想等待子线程执行结束之后再结束，比如子线程处理一个数据，
 * 主线程要取得这个数据，就要用 join() 方法。
 *
 * sleep(long) 方法在睡眠时不释放对象锁，而 join() 方法在等待的
 * 过程中释放对象锁。
 *
 * yield() 方法会临时暂停当前正在执行的线程，来让有同样优先级的
 * 正在等待的线程有机会执行。如果没有正在等待的线程，或者所有
 * 正在等待的线程的优先级都比较低，那么该线程会继续运行。执行
 * 了yield方法的线程什么时候会继续运行由线程调度器来决定。
 *
 * join作用-》https://www.cnblogs.com/aademeng/articles/10882539.html
 */