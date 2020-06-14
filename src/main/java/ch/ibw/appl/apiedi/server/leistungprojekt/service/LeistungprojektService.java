package ch.ibw.appl.apiedi.server.leistungprojekt.service;

import ch.ibw.appl.apiedi.server.leistungprojekt.model.Leistungprojekt;
import ch.ibw.appl.apiedi.server.leistungprojekt.model.ModelId;

import java.util.List;

public class LeistungprojektService {
  private final LeistungprojektRepository<Leistungprojekt> leistungprojektRepo;

  public LeistungprojektService(LeistungprojektRepository<Leistungprojekt> leistungprojektRepo) {
    this.leistungprojektRepo = leistungprojektRepo;
  }

  public List<Leistungprojekt> all() {
    return leistungprojektRepo.all();
  }

  public Leistungprojekt getById(int id) {
    return leistungprojektRepo.get(id);
  }

  public ModelId create(Leistungprojekt leistungprojekt) {
  return null;
  }
}