package ch.ibw.appl.apiedi.server.ausgaben.service;

import ch.ibw.appl.apiedi.server.behandlungen.model.ModelId;
import ch.ibw.appl.apiedi.server.ausgaben.model.Ausgabe;
import ch.ibw.appl.apiedi.server.shared.service.Repository;

import java.util.List;

public interface AusgabeRepository<T extends Ausgabe> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  Object findByDescription(String description);

}
