package ch.ibw.appl.apiedi.server.realisierungsjahr.service;

import ch.ibw.appl.apiedi.server.realisierungsjahr.model.ModelId;
import ch.ibw.appl.apiedi.server.realisierungsjahr.model.Realisierungsjahr;
import ch.ibw.appl.apiedi.server.shared.service.Repository;

import java.util.List;

public interface RealisierungsjahrRepository<T extends Realisierungsjahr> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  T findByDescription(String description);
}
