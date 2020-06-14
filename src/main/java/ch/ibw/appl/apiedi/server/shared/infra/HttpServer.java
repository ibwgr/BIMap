package ch.ibw.appl.apiedi.server.shared.infra;

import ch.ibw.appl.apiedi.server.angebot.infra.BauartController;
import ch.ibw.appl.apiedi.server.ausgaben.infra.AusgabeController;
import ch.ibw.appl.apiedi.server.bauart.infra.BauartController;
import ch.ibw.appl.apiedi.server.behandlungen.infra.BehandlungController;
import ch.ibw.appl.apiedi.server.behandlungen.service.ValidationError;
import ch.ibw.appl.apiedi.server.hello.HelloController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.LoggerFactory;
import spark.Service;

import static spark.Spark.options;

public class HttpServer {

  private final String httpPort;
  private final Boolean isTest;
  private Service server;

  public HttpServer(String httpPort, Boolean isTest) {
    this.httpPort = httpPort;
    this.isTest = isTest;
  }

  public void start() {
    server = Service.ignite();
    server.port(Integer.parseInt(httpPort));

    new ch.ibw.appl.apiedi.server.angebot.infra.BauartController(isTest).createRoutes(server);
    new BehandlungController(isTest).createRoutes(server);
    new AusgabeController(isTest).createRoutes(server);
    new HelloController(isTest).createRoutes(server);
    new BauartController(isTest).createRoutes(server);

    server.options("/*",
            (request, response) -> {

              String accessControlRequestHeaders = request
                      .headers("Access-Control-Request-Headers");
              if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
              }

              String accessControlRequestMethod = request
                      .headers("Access-Control-Request-Method");
              if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
              }

              return "OK";
            });


    server.before(((request, response) -> {
      response.header("Access-Control-Allow-Origin", "http://localhost:63343");
    }));

    server.afterAfter(((request, response) -> response.type("application/json")));

    server.exception(RuntimeException.class, (exception, request, response) -> {
      if(exception instanceof ValidationError){
        String message = ((ValidationError) exception).message;
        JsonNode node = JsonNodeFactory.instance.objectNode().set("message", JsonNodeFactory.instance.textNode(message));
        response.body(node.toString());
        response.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
      } else {
        LoggerFactory.getLogger(HttpServer.class).error(exception.toString());
        response.body("");
        response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
      }
    });

    server.notFound(((request, response) -> ""));

    server.awaitInitialization();
  }

  public void stop() {
    server.stop();
    server.awaitStop();
  }
}
