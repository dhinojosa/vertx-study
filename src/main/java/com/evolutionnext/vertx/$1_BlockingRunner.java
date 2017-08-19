package com.evolutionnext.vertx;

import io.vertx.core.Vertx;

public class $1_BlockingRunner {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        System.out.printf("In the Main thread: %s\n", Thread.currentThread().getName());
        vertx.<Integer>executeBlocking(future -> {
            System.out.printf("In Execute Blocking in Thread: %s\n", Thread.currentThread().getName());
            //Do whatever blocking code here
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete(4);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.printf("Received: %d in Thread: %s\n",
                        asyncResult.result(), Thread.currentThread().getName());
            }
        });
        Thread.sleep(5000);
        vertx.close();
    }
}
