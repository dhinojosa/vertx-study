package com.evolutionnext.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class $9_JsonMessaging {

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        JsonObject jsonObject = new JsonObject()
                .put("user", "scott")
                .put("user.directory", "/foo");

        vertx.deployVerticle("js:JavaScriptVerticleJSON.js", event -> {
            if (event.succeeded()) {
               vertx.eventBus().publish("latest-news",  jsonObject);
            }
        });

        Thread.sleep(4000);
        vertx.close();
    }
}
