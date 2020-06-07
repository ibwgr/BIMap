package ch.ibw.appl.apiedi.server.behandlungen.service;

import ch.ibw.appl.apiedi.server.behandlungen.model.Behandlung;
import ch.ibw.appl.apiedi.server.behandlungen.model.ModelId;
import ch.ibw.appl.apiedi.server.shared.service.Repository;

import java.util.List;

public interface BehandlungRepository<T extends Behandlung> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  Object findByDescription(String description);

}
