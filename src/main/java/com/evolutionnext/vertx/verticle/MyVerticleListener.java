package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticleListener extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        System.out.printf("Starting up the verticle in Thread: %s\n",
                Thread.currentThread().getName());
        getVertx().eventBus().<String>consumer("latest-news", event -> {
            System.out.format("event.body %s in Thread: %s\n", event.body(), Thread.currentThread().getName());
        });
        System.out.println("Receiver ready!");
    }

    @Override
    public void stop() throws Exception {
        System.out.printf("Shutting down the verticle in Thread: %s\n",
                Thread.currentThread().getName());
    }
}
