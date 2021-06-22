package io.leontius.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * 倒计时 主要解决一个线程等待多个线程的场景
 *
 * @author Leon Zhang
 */
@Slf4j
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("thread 1 count down.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });

        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("thread 2 count down.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });

        // 等待两个线程都执行完成。
        countDownLatch.await();
        System.out.println("thread working finished.");
    }

}
