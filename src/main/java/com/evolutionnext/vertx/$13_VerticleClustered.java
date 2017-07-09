package com.evolutionnext.vertx;

import com.evolutionnext.vertx.verticle.MyVerticleListener;
import com.hazelcast.config.Config;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class $13_VerticleClustered {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws InterruptedException {
        Config hazelcastConfig = new Config();
        ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                vertx.deployVerticle(new MyVerticleListener(), event -> {
                    if (event.succeeded()) {
                        vertx.eventBus()
                             .publish("latest-news", "Justin Bieber imprisoned, nations rejoice");
                    }
                });
            } else {

            }
        });
    }
}
