package ch.ibw.appl.apiedi.server.realisierungsjahr.infra;

import ch.ibw.appl.apiedi.server.realisierungsjahr.model.Realisierungsjahr;
import ch.ibw.appl.apiedi.server.realisierungsjahr.service.RealisierungsjahrService;
import ch.ibw.appl.apiedi.server.shared.service.JSONSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
import spark.Service;

public class RealisierungsjahrController {
  private RealisierungsjahrService realisierungsjahrService;

  public RealisierungsjahrController(Boolean isTest) {
    realisierungsjahrService = new RealisierungsjahrService(new RealisierungsjahrSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/realisierungsjahr", "application/json",
            (request, response) -> {
              response.type("application/json");
              return realisierungsjahrService.all();
            },
            jsonSerializer::serialize);

//    server.get("/todo/items", "text/csv",
//            (request, response) ->  todoItemService.all(),
//            model -> null/*make csv*/);

    server.get("/realisierungsjahr/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return realisierungsjahrService.getById(id);
    }, jsonSerializer::serialize);

    server.post("/realisierungsjahr", (request, response) -> {
      Realisierungsjahr realisierungsjahr = jsonSerializer.deserialize(request.body(), new TypeReference<Realisierungsjahr>() {});
      response.status(HttpStatus.CREATED_201);
      return realisierungsjahrService.create(realisierungsjahr);
    }, jsonSerializer::serialize);
  }
}
