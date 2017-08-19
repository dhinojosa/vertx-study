package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticleListener;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

public class $12_HTTPServer {
    public static void main(String[] args) {
        System.out.println("Here we go!");
        final Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(request -> {
            System.out.println(request.absoluteURI());
            System.out.println(request.method());
            vertx.eventBus().publish("latest-news",
                    "Request Received to: " + request.path());
            HttpServerResponse response = request.response();
            response.setStatusCode(200);
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
        vertx.deployVerticle(new MyVerticleListener(), event -> {
            if(event.succeeded()) {
                System.out.println("Verticle deployed");
            } else if (event.failed()) {
                System.out.println("No Verticle deployed");
                event.cause().printStackTrace();
            }
        });
    }
}
