package io.leontius.concurrency.thread;

/**
 * 守护线程是一种特殊的线程，就和它的名字一样，它是系统的守护者，在后台默默地守护一些系统服务，比如垃圾回收线程，JIT线程就可以理解守护线程。
 * 守护线程在退出的时候并不会执行finnaly块中的代码，所以将释放资源等操作不要放在finnaly块中执行，这种操作是不安全的。
 *
 * @author Leon Zhang
 */
public class DaemonThreadExample {

    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("im alive.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("finally block.");
                }
            }
        });

        daemonThread.setName("daemon-thread");
        // 要注意的是设置守护线程要先于start()方法
        daemonThread.setDaemon(true);
        daemonThread.start();


        // 确保主线程结束前，守护线程能分到时间片。
        try {
            Thread.sleep(1000);
            System.out.println("Current thread finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
