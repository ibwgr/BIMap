package ch.ibw.appl.apiedi.server.angebot.service;

import ch.ibw.appl.apiedi.server.behandlungen.model.ModelId;
import ch.ibw.appl.apiedi.server.behandlungen.service.ValidationError;

import java.util.List;

public class AngebotService {
  private final AngebotRepository<ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt> angebotRepo;

  public AngebotService(AngebotRepository<ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt> angebotRepo) {
    this.angebotRepo = angebotRepo;
  }

  public List<ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt> all() {
    return angebotRepo.all();
  }

  public ModelId create(ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt angebot) {
    if(angebot.behart.isEmpty()){
      throw new ValidationError("Behandlungs Art can not be empty");
    }
    if (angebot.betrag == 0) {
      throw new ValidationError("Betrag can not be empty");
    }

    return angebotRepo.add(angebot);
  }

  public ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt getById(int id) {
    return angebotRepo.get(id);
  }
}
