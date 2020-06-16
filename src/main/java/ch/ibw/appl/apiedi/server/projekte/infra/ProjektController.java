package ch.ibw.appl.apiedi.server.projekte.infra;

import ch.ibw.appl.apiedi.server.projekte.model.Projekt;
import ch.ibw.appl.apiedi.server.projekte.service.ProjektService;
import ch.ibw.appl.apiedi.server.shared.service.JSONSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
import spark.Service;

public class ProjektController {
  private ProjektService projektService;

  public ProjektController(Boolean isTest) {
    projektService = new ProjektService(new ProjektSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/projekte", (request, response) -> {
              response.type("application/json");
              return projektService.all();
            },
            jsonSerializer::serialize);

            System.out.println(projektService.all());

    server.get("/projekte/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return projektService.getById(id);
    }, jsonSerializer::serialize);
//
//    server.post("/angebote", (request, response) -> {
//      Projekt projekt = jsonSerializer.deserialize(request.body(), new TypeReference<Projekt>() {});
//      response.status(HttpStatus.CREATED_201);
//      return projektService.create(projekt);
//    }, jsonSerializer::serialize);
  }
}