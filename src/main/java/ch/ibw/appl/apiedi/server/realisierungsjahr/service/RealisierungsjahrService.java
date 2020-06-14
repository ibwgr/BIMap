package ch.ibw.appl.apiedi.server.realisierungsjahr.service;

import ch.ibw.appl.apiedi.server.realisierungsjahr.infra.RealisierungsjahrSQL2ORepository;
import ch.ibw.appl.apiedi.server.realisierungsjahr.model.ModelId;
import ch.ibw.appl.apiedi.server.realisierungsjahr.model.Realisierungsjahr;

import java.util.List;

public class RealisierungsjahrService {
  private final RealisierungsjahrRepository<Realisierungsjahr> realisierungsjahrRepo;

  public RealisierungsjahrService(RealisierungsjahrSQL2ORepository realisierungsjahrRepo) {
    this.realisierungsjahrRepo = realisierungsjahrRepo;
  }

  public List<Realisierungsjahr> all() {
    return realisierungsjahrRepo.all();
  }

  public Realisierungsjahr getById(int id) {
    return realisierungsjahrRepo.get(id);
  }

  public ModelId create(Realisierungsjahr realisierungsjahr) {
    return null;
  }
}