

var eb = vertx.eventBus();

eb.consumer("latest-news", function (message) {
    console.log("Received news on javascript!: " + message.body());
});