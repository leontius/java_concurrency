package io.leontius.concurrency.thread;

/**
 * 如果一个线程实例A执行了threadB.join(),其含义是：当前线程A会等待threadB线程终止后threadA才会继续执行。
 *
 * @author Leon Zhang
 */
public class JoinThreadExample {

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread currentThread = new JoinThread(previousThread);
            currentThread.start();
            // 接力
            previousThread = currentThread;
        }
    }

    static class JoinThread extends Thread {
        private Thread thread;

        public JoinThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                this.thread.join();
                // Thread类除了提供join()方法外，另外还提供了超时等待的方法，如果线程threadB在等待的时间内还没有结束的话，threadA会在超时之后继续执行。
                this.thread.join(100);
                System.out.println("Thread " + this.thread.getName() + " terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
