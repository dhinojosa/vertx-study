package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticleBufferListener;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;

public class $10_BufferMessaging {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyVerticleBufferListener(), event -> {
            if (event.succeeded()) {
                Buffer buffer = Buffer.buffer();
                buffer.appendString("Hello", "UTF-8");
                buffer.appendLong(50L);
                buffer.appendUnsignedShort(400);
                vertx.eventBus().send("my-buffer", buffer);
            }
        });

        Thread.sleep(15000);
        vertx.close();
    }
}
