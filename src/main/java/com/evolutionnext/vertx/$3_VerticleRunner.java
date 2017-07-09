package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticle;
import io.vertx.core.Vertx;

public class $3_VerticleRunner {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyVerticle());
        Thread.sleep(5000);
        vertx.close();
    }
}
