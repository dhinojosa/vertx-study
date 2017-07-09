package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticleListener;
import com.hazelcast.config.Config;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class $14_HTTPServerClustered {
    public static void main(String[] args) {
        System.out.println("Here we go!");
        Config hazelcastConfig = new Config();
        ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, event -> {
            if (event.succeeded()) {
                Vertx result = event.result();
                EventBus eventBus = result.eventBus();
                HttpServer httpServer = result.createHttpServer();
                httpServer.requestHandler(request -> {
                    eventBus.publish("latest-news", "Request Received to: " + request.path());
                    HttpServerResponse response = request.response();
                    response.end("Message Received");
                });
                httpServer.listen(8082, httpServerAsyncResult -> {
                    if (httpServerAsyncResult.succeeded()) {
                        System.out.println("Server is bound: " + httpServerAsyncResult.result().actualPort());
                    } else if (httpServerAsyncResult.failed()) {
                        System.out.println("Unable to set up server");
                        httpServerAsyncResult.cause().printStackTrace();
                    }
                });
                result.deployVerticle(new MyVerticleListener(), verticleEvent -> {
                    if(verticleEvent.succeeded()) {
                        System.out.println("Verticle deployed");
                    } else if (verticleEvent.failed()) {
                        System.out.println("No Verticle deployed");
                        verticleEvent.cause().printStackTrace();
                    }
                });
            }
        });
    }
}
