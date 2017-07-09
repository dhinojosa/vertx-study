package com.evolutionnext.vertx;

import io.vertx.core.Vertx;

public class $1_BlockingRunner {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        System.out.printf("In the Main thread: %s\n", Thread.currentThread().getName());
        vertx.<Integer>executeBlocking(event -> {
            System.out.printf("In Execute Blocking in Thread: %s\n", Thread.currentThread().getName());
            event.complete(4);
        }, event -> {
            if (event.succeeded()) {
                System.out.printf("Received: %d in Thread: %s\n", event.result(), Thread.currentThread().getName());
            }
        });

        Thread.sleep(5000);
        vertx.close();
    }
}
