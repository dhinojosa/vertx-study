package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticle;
import com.evolutionnext.vertx.verticle.MyVerticleListenerWithReply;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class $6_VerticleRunnerPointToPointWithReply {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle(new MyVerticleListenerWithReply(), options, event -> {
            if (event.succeeded()) {
                vertx.eventBus().<String>send("latest-news",
                        "Justin Bieber imprisoned, nations rejoice",
                        event1 ->
                                System.out.printf("Received Result %s in Thread: %s",
                                        event1.result().body(), Thread.currentThread().getName()));
            }
        });
        Thread.sleep(15000);
        vertx.close();
    }
}
