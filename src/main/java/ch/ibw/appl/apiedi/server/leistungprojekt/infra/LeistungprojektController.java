package ch.ibw.appl.apiedi.server.leistungprojekt.infra;

import ch.ibw.appl.apiedi.server.leistungprojekt.model.Leistungprojekt;
import ch.ibw.appl.apiedi.server.leistungprojekt.service.LeistungprojektService;
import ch.ibw.appl.apiedi.server.shared.service.JSONSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
import spark.Service;

public class LeistungprojektController {
  private LeistungprojektService leistungprojektService;

  public LeistungprojektController(Boolean isTest) {
    leistungprojektService = new LeistungprojektService(new LeistungprojektSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/leistungprojekt", "application/json",
            (request, response) -> {
              response.type("application/json");
              return leistungprojektService.all();
            },
            jsonSerializer::serialize);

//    server.get("/todo/items", "text/csv",
//            (request, response) ->  todoItemService.all(),
//            model -> null/*make csv*/);

    server.get("/angebote/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return leistungprojektService.getById(id);
    }, jsonSerializer::serialize);

    server.post("/angebote", (request, response) -> {
      Leistungprojekt leistungprojekt = jsonSerializer.deserialize(request.body(), new TypeReference<Leistungprojekt>() {});
      response.status(HttpStatus.CREATED_201);
      return leistungprojektService.create(leistungprojekt);
    }, jsonSerializer::serialize);
  }
}
