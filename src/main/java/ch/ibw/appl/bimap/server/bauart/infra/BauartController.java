package ch.ibw.appl.bimap.server.bauart.infra;

import ch.ibw.appl.bimap.server.bauart.service.BauartService;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import spark.Service;

public class BauartController {
  private BauartService bauartService;

  public BauartController(Boolean isTest) {
    bauartService = new BauartService(new BauartSQL2ORepository(isTest));
  }

  public void createRoutes(Service server) {
    JSONSerializer jsonSerializer = new JSONSerializer();

    server.get("/bauarten", "application/json",
            (request, response) -> {
              return bauartService.all();
            }, jsonSerializer::serialize);


//    server.get("/bauarten", (request, response) -> {
//      int id = Integer.parseInt(request.params("id"));
//      return bauartService.getById(id);
//    }, jsonSerializer::serialize);

//    server.post("/bauart", (request, response) -> {
//      Bauart bauart = jsonSerializer.deserialize(request.body(), new TypeReference<Bauart>() {});
//      response.status(HttpStatus.CREATED_201);
//      return bauartService.create(bauart);
//    }, jsonSerializer::serialize);
  }
}