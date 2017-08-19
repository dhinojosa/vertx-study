package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticleListener;
import io.vertx.core.Vertx;

public class $8_VerticleWithPeriodic {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyVerticleListener());
        long id = vertx.setPeriodic(1000,
                aLong -> vertx.eventBus().publish("latest-news",
                        "Here is your one second notice, bring out the finest wines and cheeses"));

        Thread.sleep(5000);
        vertx.cancelTimer(id);
        Thread.sleep(1200);
        vertx.close();
    }
}
