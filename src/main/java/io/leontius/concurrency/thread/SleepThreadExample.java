package io.leontius.concurrency.thread;

/**
 * sleep()方法是Thread的静态方法，而wait()方法是Object实例方法
 * wait()方法必须要在同步方法或者同步块中调用，也就是必须已经获得对象锁。而sleep()方法没有这个限制可以在任何地方种使用。
 * 另外，wait()方法会释放占有的对象锁，使得该线程进入等待池中，等待下一次获取资源。而sleep()方法只是会让出CPU并不会释放掉对象锁；
 * sleep()方法在休眠时间达到后如果再次获得CPU时间片就会继续执行，而wait()方法必须等待Object.notift/Object.notifyAll通知后，才会离开等待池，并且再次获得CPU时间片才会继续执行。
 *
 * @author Leon Zhang
 */
public class SleepThreadExample {

    public static void main(String[] args) {
        sleepThread();

        Thread thread = new Thread(() -> {
            System.out.println("thread finished.");
            System.out.println(System.currentTimeMillis());
        });

        Thread waitThread = new Thread(() -> {
            synchronized (thread) {
                try {
                    // 方法必须要在同步方法或者同步块中调用，也就是必须已经获得对象锁。
                    thread.wait(15000);
                    System.out.println("thread wait 15000.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
            }
        });

        Thread notifyThread = new Thread(() -> {
            synchronized (thread) {
                thread.notify();
                System.out.println("thread notify.");
                System.out.println(System.currentTimeMillis());
            }
        });

        thread.start();
        waitThread.start();
        notifyThread.start();
    }

    public static void sleepThread() {
        try {
            Thread.sleep(3000);
            System.out.println("thread sleep 3000.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
