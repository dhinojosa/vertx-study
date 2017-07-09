package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.CaseInsensitiveHeaders;

import java.time.Instant;

public class MyVerticleEventBus extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        getVertx().eventBus().<String>consumer("latest-news", message -> {
            System.out.println("Received Message: " + message.body());
            DeliveryOptions deliveryOptions = new DeliveryOptions();
            deliveryOptions.setSendTimeout(4000);
            CaseInsensitiveHeaders headers = new CaseInsensitiveHeaders();
            headers.add("timestamp", Instant.now().toString());
            deliveryOptions.setHeaders(headers);
            message.reply("Got it! Appreciate it!", deliveryOptions);
        });
        startFuture.complete();
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.println("Shutting Down My Verticle");
        stopFuture.complete();
    }
}
