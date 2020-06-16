package ch.ibw.appl.bimap.server.projekte.service;


import ch.ibw.appl.bimap.server.projekte.infra.ProjektSQL2ORepository;
import ch.ibw.appl.bimap.server.projekte.model.ModelId;
import ch.ibw.appl.bimap.server.projekte.model.Projekt;

import java.util.List;

public class ProjektService {
  private final ProjektSQL2ORepository projektRepo;

  public ProjektService(ProjektSQL2ORepository projektRepo) {
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
