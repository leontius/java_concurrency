package io.leontius.concurrency.deadlock;

/**
 * 死锁例子 临界区线程安全问题
 *
 * 1、避免一个线程同时获得多个锁；
 * 2、避免一个线程在锁内部占有多个资源，尽量保证每个锁只占用一个资源；
 * 3、尝试使用定时锁，使用lock.tryLock(timeOut)，当超时等待时当前线程不会阻塞；
 * 4、对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况；
 *
 * jstack
 *
 * "thread-a":
 *         at io.leontius.concurrency.deadlock.DeadLockExample.lambda$main$0(DeadLockExample.java:20)
 *         - waiting to lock <0x00000007bfd00798> (a java.lang.String)
 *         - locked <0x00000007bfd00768> (a java.lang.String)
 *         at io.leontius.concurrency.deadlock.DeadLockExample$$Lambda$14/0x0000000840063440.run(Unknown Source)
 *         at java.lang.Thread.run(java.base@11.0.10/Thread.java:834)
 * "thread-b":
 *         at io.leontius.concurrency.deadlock.DeadLockExample.lambda$main$1(DeadLockExample.java:32)
 *         - waiting to lock <0x00000007bfd00768> (a java.lang.String)
 *         - locked <0x00000007bfd00798> (a java.lang.String)
 *         at io.leontius.concurrency.deadlock.DeadLockExample$$Lambda$15/0x0000000840067840.run(Unknown Source)
 *         at java.lang.Thread.run(java.base@11.0.10/Thread.java:834)
 *
 * Found 1 deadlock.
 *
 * @author Leon Zhang
 */
public class DeadLockExample {

    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (A) {
                System.out.println("get resource a.");
                try {
                    Thread.sleep(3000);
                    synchronized (B) {
                        System.out.println("get resource b.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (B) {
                System.out.println("get resource b.");
                synchronized (A) {
                    System.out.println("get resource a.");
                }
            }
        });

        threadA.setName("thread-a");
        threadB.setName("thread-b");

        threadA.start();
        threadB.start();
    }
}
