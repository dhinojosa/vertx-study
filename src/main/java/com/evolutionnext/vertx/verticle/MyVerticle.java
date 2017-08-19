package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        System.out.printf("Starting up the Verticle in Thread: %s\n",
                Thread.currentThread().getName());
        //doSomething
        startFuture.complete(); //Send when complete
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.printf("Shutting down the Verticle in Thread: %s\n",
                Thread.currentThread().getName());
        stopFuture.complete(); //Send when done shutting down
    }
}
