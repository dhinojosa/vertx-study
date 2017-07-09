

var eb = vertx.eventBus();

eb.consumer("latest-news", function (message) {
     console.log("Here is the latest JSON message" + (JSON.stringify(message.body())));
});