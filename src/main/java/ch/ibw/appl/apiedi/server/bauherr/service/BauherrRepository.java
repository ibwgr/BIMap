package ch.ibw.appl.apiedi.server.bauherr.service;

import ch.ibw.appl.apiedi.server.bauart.model.ModelId;
import ch.ibw.appl.apiedi.server.bauherr.model.Bauherr;
import ch.ibw.appl.apiedi.server.shared.service.Repository;

import java.util.List;

public interface BauherrRepository<T extends Bauherr> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  T findByDescription(String description);
}
