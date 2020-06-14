package ch.ibw.appl.apiedi.server.projekte.service;


import ch.ibw.appl.apiedi.server.projekte.model.ModelId;
import ch.ibw.appl.apiedi.server.projekte.model.Projekt;

import java.util.List;

public class ProjektService {
  private final ProjektRepository<Projekt> projektRepo;

  public ProjektService(ProjektRepository<Projekt> projektRepo) {
    this.projektRepo = projektRepo;
  }

  public List<Projekt> all() {
    return projektRepo.all();
  }

  public Projekt getById(int id) {
    return projektRepo.get(id);
  }

  public ModelId create(Projekt projekt) {
    return null;
  }
}
