package io.leontius.concurrency.sync;

/**
 * synchronized就具有使每个线程依次排队操作共享变量的功能。
 *
 * javap -v SynchronizedExample.class 查看字节码文件。
 *
 * @author Leon Zhang
 */
public class SynchronizedExample {

    public static void main(String[] args) {
        // monitorenter
        synchronized (SynchronizedExample.class) {
        }
        // monitorexit
        monitor();
    }

    private static void monitor() {
        System.out.println("1111111111.");
    }

}
