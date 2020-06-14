package ch.ibw.appl.apiedi.server.leistung.infra;

import ch.ibw.appl.apiedi.server.leistung.model.Leistung;
import ch.ibw.appl.apiedi.server.leistung.service.LeistungService;
import ch.ibw.appl.apiedi.server.shared.service.JSONSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
import spark.Service;

public class LeistungController {
  private LeistungService leistungService;

  public LeistungController(Boolean isTest) {
    leistungService = new LeistungService(new LeistungSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/leistung", "application/json",
            (request, response) -> {
              response.type("application/json");
              return leistungService.all();
            },
            jsonSerializer::serialize);

//    server.get("/todo/items", "text/csv",
//            (request, response) ->  todoItemService.all(),
//            model -> null/*make csv*/);

    server.get("/angebote/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return leistungService.getById(id);
    }, jsonSerializer::serialize);

    server.post("/angebote", (request, response) -> {
      Leistung leistungprojekt = jsonSerializer.deserialize(request.body(), new TypeReference<Leistung>() {});
      response.status(HttpStatus.CREATED_201);
      return leistungService.create(leistungprojekt);
    }, jsonSerializer::serialize);
  }
}
