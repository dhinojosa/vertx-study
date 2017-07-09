package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;

public class MyVerticleListenerWithReply extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        System.out.printf("Starting up the verticle in Thread: %s\n", Thread.currentThread().getName());
        getVertx().eventBus().<String>consumer("latest-news", event -> {
            System.out.println("event.body() = " + event.body());
            event.reply("That is great news!");
        });
        System.out.println("Receiver ready!");
    }

    @Override
    public void stop() throws Exception {
        System.out.printf("Shutting down the verticle in Thread: %s\n", Thread.currentThread().getName());
    }
}
