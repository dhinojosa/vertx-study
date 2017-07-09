package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;

public class MyVerticleBeta extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        getVertx().eventBus().<Buffer>consumer("my-buffer", message -> {
            Buffer body = message.body();
            System.out.println("Received: " + body.getString(0, 5));
            System.out.println("Received: " + body.getLong(6));
            System.out.println("Received: " + body.getUnsignedShort(7));
        });
        startFuture.complete();
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.println("Shutting Down My Verticle");
        stopFuture.complete();
    }
}
