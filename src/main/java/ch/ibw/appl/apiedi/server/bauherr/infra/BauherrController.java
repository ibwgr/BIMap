package ch.ibw.appl.apiedi.server.bauherr.infra;

import ch.ibw.appl.apiedi.server.bauherr.model.Bauherr;
import ch.ibw.appl.apiedi.server.bauherr.service.BauherrService;
import ch.ibw.appl.apiedi.server.shared.service.JSONSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
import spark.Service;

public class BauherrController {
  private BauherrService bauerrService;

  public BauherrController(Boolean isTest) {
    bauerrService = new BauherrService(new BauherrSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/bauerr", "application/json",
            (request, response) -> {
              response.type("application/json");
              return bauerrService.all();
            },
            jsonSerializer::serialize);

//    server.get("/todo/items", "text/csv",
//            (request, response) ->  todoItemService.all(),
//            model -> null/*make csv*/);

    server.get("/bauerr", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return bauerrService.getById(id);
    }, jsonSerializer::serialize);

    server.post("/bauerr", (request, response) -> {
      Bauherr bauerr = jsonSerializer.deserialize(request.body(), new TypeReference<Bauherr>() {});
      response.status(HttpStatus.CREATED_201);
      return bauerrService.create(bauerr);
    }, jsonSerializer::serialize);
  }
}
