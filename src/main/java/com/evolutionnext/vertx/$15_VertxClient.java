package com.evolutionnext.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

public class $15_VertxClient {

    public static void main(String[] args) {
        WebClient client = WebClient.create(Vertx.vertx(), new WebClientOptions()
                .setDefaultHost("query.yahooapis.com")
                .setSsl(true)
                .setTrustAll(true)
                .setDefaultPort(443)
                .setKeepAlive(true));
        client
                .get("/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22ORCL%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=")
                .send(ar -> {
                    if (ar.succeeded()) {
                        HttpResponse<Buffer> response = ar.result();
                        JsonObject entries = new JsonObject(response.body());

                        JsonObject quote = entries.getJsonObject("query")
                                                  .getJsonObject("results")
                                                  .getJsonObject("quote");

                        Double lastTradePriceOnly =
                                Double.parseDouble(quote
                                        .getString("LastTradePriceOnly"));

                        System.out.format("Received response with status code: %d\n", response.statusCode());
                        System.out.format("Oracle's last Trade Price was: %.2f\n", lastTradePriceOnly);
                    } else {
                        System.out.println("Something went wrong " + ar.cause());
                    }
                });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);
    }
}
