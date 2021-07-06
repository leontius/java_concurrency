package io.leontius.concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Leon Zhang
 */
public class CASExample {

    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        // 启动5根线程
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
               try {
                   Thread.sleep(10);
               } catch (Exception e) {
                   e.printStackTrace();
               }

                for (int j = 0; j < 100; j++) {
                    // 先+1后返回
                    count.incrementAndGet();
                }
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出结果500
        System.out.println(count);
    }

}
