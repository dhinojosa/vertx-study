package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;

public class MyVerticleReply extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        System.out.printf("Starting up the verticle in Thread: %s\n", Thread.currentThread().getName());
        getVertx().eventBus().<String>consumer("latest-news", event -> event.reply("Got it!"));
        startFuture.complete();
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.printf("Shutting down the verticle in Thread: %s\n", Thread.currentThread().getName());
        stopFuture.complete(); //Send when done shutting down
    }
}
