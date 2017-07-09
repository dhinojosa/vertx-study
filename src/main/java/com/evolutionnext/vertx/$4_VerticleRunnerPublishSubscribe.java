package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticle;
import com.evolutionnext.vertx.verticle.MyVerticleListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

public class $4_VerticleRunnerPublishSubscribe {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle(new MyVerticleListener(), options,
                event -> {
                    if (event.succeeded()) {
                        vertx
                                .eventBus()
                                .publish("latest-news", "Justin Bieber imprisoned, nations rejoice");
                    }
                });
        System.out.println("Should be sent");
        Thread.sleep(15000);
        vertx.close();
    }
}
