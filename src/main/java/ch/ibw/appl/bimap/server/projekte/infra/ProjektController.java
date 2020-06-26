package ch.ibw.appl.bimap.server.projekte.infra;

import ch.ibw.appl.bimap.server.projekte.service.ProjektService;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import spark.Service;

public class ProjektController {
    private ProjektService projektService;

    public ProjektController(Boolean isTest, String username, String password) {
        projektService = new ProjektService(new ProjektSQL2ORepository(isTest, username, password));
    }

    public void createRoutes(Service server) {
        JSONSerializer jsonSerializer = new JSONSerializer();

        server.get("/projekte", (request, response) -> {
            String bauherr = request.queryParamOrDefault("bauherr", "");
            String bauart = request.queryParamOrDefault("bauart", "");
            int realisierungsjahr = Integer.parseInt(request.queryParamOrDefault("realisierungsjahr", "0"));

            return projektService.getByFilter(bauherr, bauart, realisierungsjahr);
            }, jsonSerializer::serialize);

        server.get("/projekte/:id", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            return projektService.getById(id);
        }, jsonSerializer::serialize);
    }
}
