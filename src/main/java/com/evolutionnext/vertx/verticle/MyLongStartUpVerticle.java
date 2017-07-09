package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyLongStartUpVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
           Thread.sleep(5000);
           startFuture.complete();
    }
}
