package ch.ibw.appl.apiedi.server.angebot.service;

import ch.ibw.appl.apiedi.server.behandlungen.model.ModelId;
import ch.ibw.appl.apiedi.server.behandlungen.service.ValidationError;
import ch.ibw.appl.apiedi.server.angebot.model.Angebot;

import java.util.List;

public class AngebotService {
  private final AngebotRepository<Angebot> angebotRepo;

  public AngebotService(AngebotRepository<Angebot> angebotRepo) {
    this.angebotRepo = angebotRepo;
  }

  public List<Angebot> all() {
    return angebotRepo.all();
  }

  public ModelId create(Angebot angebot) {
    if(angebot.behart.isEmpty()){
      throw new ValidationError("Behandlungs Art can not be empty");
    }
    if (angebot.betrag == 0) {
      throw new ValidationError("Betrag can not be empty");
    }

    return angebotRepo.add(angebot);
  }

  public Angebot getById(int id) {
    return angebotRepo.get(id);
  }
}
