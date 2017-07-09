package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;

import java.util.concurrent.atomic.AtomicInteger;

public class MyStateVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        SharedData sharedData = getVertx().sharedData();
        LocalMap<String, Integer> localMap = sharedData.getLocalMap("counters");
        System.out.printf("Starting up the MyStateVerticle in Thread: %s\n", Thread.currentThread().getName());
        getVertx().eventBus().consumer("counter-add", event -> {
            localMap.replace("my-state", localMap.get("my-state") + 1);
        });
        getVertx().eventBus().consumer("counter-view", event -> {
            localMap.get("my-state");
        });
        startFuture.complete(); //Send when complete
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.printf("Shutting down the MyStateverticle in Thread: %s\n", Thread.currentThread().getName());
        stopFuture.complete(); //Send when done shutting down
    }
}
