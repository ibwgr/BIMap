package ch.ibw.appl.bimap.server.bauaherr.infra;

import ch.ibw.appl.bimap.server.bauaherr.service.BauherrService;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import spark.Service;

public class BauherrController {
  private BauherrService bauherrService;

  public BauherrController(Boolean isTest, String username, String password) {
    bauherrService = new BauherrService(new BauherrSQL2ORepository(isTest, username, password));
  }
    JSONSerializer jsonSerializer = new JSONSerializer();

  public void createRoutes(Service server) {
    server.get("/bauherren", "application/json",
            (request, response) -> {
              return bauherrService.all();
            }, jsonSerializer::serialize);

    server.get("/bauherr/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return bauherrService.getById(id);
    }, jsonSerializer::serialize);

  }
}