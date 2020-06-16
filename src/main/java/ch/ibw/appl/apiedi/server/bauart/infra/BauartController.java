package ch.ibw.appl.apiedi.server.bauart.infra;


import ch.ibw.appl.apiedi.server.bauart.model.Bauart;
import ch.ibw.appl.apiedi.server.bauart.service.BauartService;
import ch.ibw.appl.apiedi.server.shared.service.JSONSerializer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
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
              response.type("application/json");
              return bauartService.all();
            },
            jsonSerializer::serialize);

            System.out.println(bauartService.all());

//    server.get("/todo/items", "text/csv",
//            (request, response) ->  todoItemService.all(),
//            model -> null/*make csv*/);

    server.get("/bauarten", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      return bauartService.getById(id);
    }, jsonSerializer::serialize);

//    server.post("/bauart", (request, response) -> {
//      Bauart bauart = jsonSerializer.deserialize(request.body(), new TypeReference<Bauart>() {});
//      response.status(HttpStatus.CREATED_201);
//      return bauartService.create(bauart);
//    }, jsonSerializer::serialize);
  }
}