package io.leontius.concurrency.thread;

/**
 * 中断可以理解为线程的一个标志位，它表示了一个运行中的线程是否被其他线程进行了中断操作。
 *
 * @author Leon Zhang
 */
public class InterruptExample {

    public static void main(String[] args) {
        // 睡眠线程
        final Thread sleepThread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };

        // 一直执行死循环线程
        Thread busyThread = new Thread(() -> {
            while (true) ;
        });

        sleepThread.start();
        busyThread.start();

        sleepThread.interrupt();
        busyThread.interrupt();

        while (sleepThread.isInterrupted()) ;

        System.out.println(String.format("sleep thread is interrupted: %b", sleepThread.isInterrupted()));
        System.out.println(String.format("busy thread is interrupted: %b", busyThread.isInterrupted()));
    }

}
