package ch.ibw.appl.apiedi.server.angebot.infra;

import ch.ibw.appl.apiedi.server.shared.service.JSONSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
import spark.Service;

public class AngebotController {
  private ch.ibw.appl.apiedi.server.angebot.service.LeistungprojektService angebotService;

  public AngebotController(Boolean isTest) {
    angebotService = new ch.ibw.appl.apiedi.server.angebot.service.LeistungprojektService(new AngebotSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/angebote", "application/json",
            (request, response) -> {
              response.type("application/json");
              return angebotService.all();
            },
            jsonSerializer::serialize);

//    server.get("/todo/items", "text/csv",
//            (request, response) ->  todoItemService.all(),
//            model -> null/*make csv*/);

    server.get("/angebote/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return angebotService.getById(id);
    }, jsonSerializer::serialize);

    server.post("/angebote", (request, response) -> {
      ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt angebot = jsonSerializer.deserialize(request.body(), new TypeReference<ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt>() {});
      response.status(HttpStatus.CREATED_201);
      return angebotService.create(angebot);
    }, jsonSerializer::serialize);
  }
}
