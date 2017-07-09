package com.evolutionnext.vertx;

import com.evolutionnext.vertx.pojo.Employee;
import com.evolutionnext.vertx.codec.EmployeeMessageCodec;
import com.evolutionnext.vertx.verticle.MyVerticleListenerCustomObject;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;

public class $11_CustomMessaging {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();

        EmployeeMessageCodec codec = new EmployeeMessageCodec();
        DeliveryOptions options = new DeliveryOptions().setCodecName(codec.name());

        vertx.deployVerticle(new MyVerticleListenerCustomObject(), event -> {
            if (event.succeeded()) {
                vertx.eventBus().registerCodec(codec);
                vertx.eventBus().publish("my-object", new Employee("Nicolas", "Cage"), options);
            }
        });
        Thread.sleep(15000);
        vertx.close();
    }

}
