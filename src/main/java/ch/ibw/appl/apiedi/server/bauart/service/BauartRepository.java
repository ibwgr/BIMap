package ch.ibw.appl.apiedi.server.bauart.service;

import ch.ibw.appl.apiedi.server.bauart.model.Bauart;
import ch.ibw.appl.apiedi.server.bauart.model.ModelId;
import ch.ibw.appl.apiedi.server.shared.service.Repository;

import java.util.List;

public interface BauartRepository<T extends Bauart> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  T findByDescription(String description);
}
