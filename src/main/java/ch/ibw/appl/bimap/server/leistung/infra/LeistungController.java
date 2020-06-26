package ch.ibw.appl.bimap.server.leistung.infra;

import ch.ibw.appl.bimap.server.leistung.service.LeistungService;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import spark.Service;

public class LeistungController {
  private LeistungService leistungService;

  public LeistungController(Boolean isTest) {
    leistungService = new LeistungService(new LeistungSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/leistungen", "application/json",
            (request, response) -> {
              response.type("application/json");
              return leistungService.all();
            },
            jsonSerializer::serialize);
  }
}
