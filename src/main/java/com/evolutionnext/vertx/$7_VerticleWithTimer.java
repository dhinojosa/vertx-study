package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticle;
import com.evolutionnext.vertx.verticle.MyVerticleListener;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;

public class $7_VerticleWithTimer {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions()
                .setWorkerPoolName("external-pool")
                .setWorkerPoolSize(2)
                .setWorker(true);
        vertx.deployVerticle(new MyVerticleListener(), options);
        System.out.println("Timer is set");
        vertx.setTimer(4000, aLong ->
                vertx.eventBus().publish("latest-news",
                        "It's been four seconds since we deployed, " +
                                "bring out the finest wines and cheeses"));

        Thread.sleep(15000);
        vertx.close();
    }
}
