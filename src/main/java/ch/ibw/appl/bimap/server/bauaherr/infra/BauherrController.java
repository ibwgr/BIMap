package ch.ibw.appl.bimap.server.bauaherr.infra;

import ch.ibw.appl.bimap.server.bauaherr.service.BauherrService;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import spark.Service;

public class BauherrController {
  private BauherrService bauherrService;

  public BauherrController(Boolean isTest) {
    bauherrService = new BauherrService(new BauherrSQL2ORepository(isTest));
  }
    JSONSerializer jsonSerializer = new JSONSerializer();

  public void createRoutes(Service server) {
    server.get("/bauherren", "application/json",
            (request, response) -> {
              return bauherrService.all();
            }, jsonSerializer::serialize);

            System.out.println("TESTESTEST");
            System.out.println(bauherrService.all());

    server.get("/bauherr/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return bauherrService.getById(id);
    }, jsonSerializer::serialize);

//    server.post("/bauart", (request, response) -> {
//      Bauart bauart = jsonSerializer.deserialize(request.body(), new TypeReference<Bauart>() {});
//      response.status(HttpStatus.CREATED_201);
//      return bauartService.create(bauart);
//    }, jsonSerializer::serialize);
  }
}