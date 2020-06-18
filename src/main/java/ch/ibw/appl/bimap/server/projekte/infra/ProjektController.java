package ch.ibw.appl.bimap.server.projekte.infra;

import ch.ibw.appl.bimap.server.projekte.service.ProjektService;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import spark.Service;

public class ProjektController {
  private ProjektService projektService;

  public ProjektController(Boolean isTest) {
    projektService = new ProjektService(new ProjektSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/projekte", (request, response) -> {
                String bauherr = request.queryParamOrDefault("bauherr", "");
                String bauart = request.queryParamOrDefault("bauart", "");
                int realisierungsjahr = Integer.parseInt(request.queryParamOrDefault("realisiserungsjahr", "0"));

              response.type("application/json");

              return projektService.getByFilter(bauherr, bauart, realisierungsjahr);
            },
            jsonSerializer::serialize);

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
