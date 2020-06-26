package ch.ibw.appl.bimap.server.bauart.infra;

import ch.ibw.appl.bimap.server.bauart.service.BauartService;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import spark.Service;

public class BauartController {
  private BauartService bauartService;

  public BauartController(Boolean isTest, String username, String password) {
    bauartService = new BauartService(new BauartSQL2ORepository(isTest, username, password));
  }
    JSONSerializer jsonSerializer = new JSONSerializer();

  public void createRoutes(Service server) {
    server.get("/bauarten", "application/json",
            (request, response) -> {
              return bauartService.all();
            }, jsonSerializer::serialize);

    server.get("/bauarten/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return bauartService.getById(id);
    }, jsonSerializer::serialize);
  }
}