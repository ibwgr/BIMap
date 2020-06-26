package ch.ibw.appl.bimap.server.leistung.service;

import ch.ibw.appl.bimap.server.leistung.infra.LeistungSQL2ORepository;
import ch.ibw.appl.bimap.server.leistung.model.Leistung;

import java.util.List;

public class LeistungService {
  private final LeistungSQL2ORepository leistungRepo;

  public LeistungService(LeistungSQL2ORepository leistungRepo) {
    this.leistungRepo = leistungRepo;
  }

  public List<Leistung> all() {
    return leistungRepo.all();
  }

  public Leistung getById(int id) {
    return leistungRepo.get(id);
  }
}