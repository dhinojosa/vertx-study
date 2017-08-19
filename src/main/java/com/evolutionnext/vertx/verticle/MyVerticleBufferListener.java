package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageConsumer;

public class MyVerticleBufferListener extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        MessageConsumer<Buffer> consumer =
                getVertx().eventBus().consumer("my-buffer");
        consumer.handler(event -> {
            Buffer body = event.body();
            System.out.println(body.getString(0, 5, "UTF-8"));
            System.out.println(body.getLong(5));
            System.out.println(body.getUnsignedShort(13));
        });
        startFuture.complete();
    }


    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.println("Shutting Down My Verticle");
        stopFuture.complete();
    }
}
