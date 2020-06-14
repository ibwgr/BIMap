package ch.ibw.appl.apiedi.server.bauherr.service;

import ch.ibw.appl.apiedi.server.bauart.model.ModelId;
import ch.ibw.appl.apiedi.server.bauherr.model.Bauherr;

import java.util.List;

public class BauherrService {
  private final BauherrRepository<Bauherr> bauerrRepo;

  public BauherrService(BauherrRepository<Bauherr> bauerrRepo) {
    this.bauerrRepo = bauerrRepo;
  }

  public List<Bauherr> all() {
    return bauerrRepo.all();
  }

  public Bauherr getById(int id) {
    return bauerrRepo.get(id);
  }

  public ModelId create(Bauherr bauerr) {
    return null;
  }
}