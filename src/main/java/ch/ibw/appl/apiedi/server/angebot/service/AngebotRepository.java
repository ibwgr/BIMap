package ch.ibw.appl.apiedi.server.angebot.service;

import ch.ibw.appl.apiedi.server.angebot.model.Angebot;
import ch.ibw.appl.apiedi.server.behandlungen.model.ModelId;
import ch.ibw.appl.apiedi.server.shared.service.Repository;

import java.util.List;

public interface AngebotRepository<T extends Angebot> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  T findByDescription(String description);

}
