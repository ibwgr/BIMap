package ch.ibw.appl.bimap.server.bauaherr.service;

import ch.ibw.appl.bimap.server.bauaherr.infra.BauherrSQL2ORepository;
import ch.ibw.appl.bimap.server.bauaherr.model.Bauherr;
import ch.ibw.appl.bimap.server.bauaherr.model.ModelId;


import java.util.List;

public class BauherrService {
  private final BauherrSQL2ORepository bauherrRepo;

  public BauherrService (BauherrSQL2ORepository bauherrRepo) {
    this.bauherrRepo = bauherrRepo;
  }

  public List<Bauherr> all() {
    return bauherrRepo.all();
  }

  public Bauherr getById(int id) {
    return bauherrRepo.get(id);
  }

  public ModelId create(Bauherr bauherr) {
    return null;
  }
}