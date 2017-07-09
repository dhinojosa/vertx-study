package com.evolutionnext.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class MyVerticleWeatherAPI extends AbstractVerticle {

    private static String urlString = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        getVertx().eventBus().<String>consumer("request-weather", event -> {
            URL url = null;
            try {
                url = new URL(String.format(urlString, event.body()));
            } catch (MalformedURLException e) {
                event.fail(5, e.getMessage());
            }

            try (InputStream inputStream = url.openConnection().getInputStream();
                 Reader reader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                new JsonObject(bufferedReader.lines().collect(Collectors.joining("\\n")));
            } catch (MalformedURLException e) {
                event.fail(10, e.getMessage());
            } catch (IOException e) {
                event.fail(11, e.getMessage());
            }
        });
        startFuture.complete();
    }


    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.println("Shutting Down My Verticle");
        stopFuture.complete();
    }
}
