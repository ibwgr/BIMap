package ch.ibw.appl.apiedi.server.bauart.service;

import ch.ibw.appl.apiedi.server.bauart.infra.BauartSQL2ORepository;
import ch.ibw.appl.apiedi.server.bauart.model.Bauart;
import ch.ibw.appl.apiedi.server.bauart.model.ModelId;


import java.util.List;

public class BauartService {
  private final BauartSQL2ORepository bauartRepo;

  public BauartService (BauartSQL2ORepository bauartRepo) {
    this.bauartRepo = bauartRepo;
  }

  public List<Bauart> all() {
    return bauartRepo.all();
  }

  public Bauart getById(int id) {
    return bauartRepo.get(id);
  }

  public ModelId create(Bauart bauart) {
    return null;
  }
}