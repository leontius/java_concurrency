package io.leontius.concurrency.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 新建线程。
 *
 * 1、通过继承Thread类，重写run方法；
 *
 * 2、通过实现runable接口；
 *
 * 3、通过实现callable接口这三种方式。
 *
 * @author Leon Zhang
 */
public class CreateThreadExample {

    public static void main(String[] args) {
        // 继承Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("继承Thread.");
                super.run();
            }
        };

        thread.start();

        // 实现Runnable
        Runnable runnable = () -> System.out.println("实现Runnable.");
        Thread thread1 = new Thread(runnable);
        thread1.start();

        // 实现Callable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            System.out.println("实现Callable.");
            return "Obj";
        };
        Future<String> future = executorService.submit(callable);
        try {
            // 返回值
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
