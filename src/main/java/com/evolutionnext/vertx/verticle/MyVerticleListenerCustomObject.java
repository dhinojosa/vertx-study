package com.evolutionnext.vertx.verticle;

import com.evolutionnext.vertx.pojo.Employee;
import io.vertx.core.AbstractVerticle;

public class MyVerticleListenerCustomObject extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        System.out.printf("Starting up the verticle in Thread: %s\n", Thread.currentThread().getName());
        getVertx().eventBus().<Employee>consumer("my-object", event -> {
            System.out.println("event.body() = " + event.body());
        });
        System.out.println("Receiver ready!");
    }

    @Override
    public void stop() throws Exception {
        System.out.printf("Shutting down the verticle in Thread: %s\n", Thread.currentThread().getName());
    }
}
