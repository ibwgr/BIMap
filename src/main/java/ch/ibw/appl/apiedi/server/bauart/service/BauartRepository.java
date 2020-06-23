package ch.ibw.appl.apiedi.server.bauart.service;


import ch.ibw.appl.bimap.server.bauart.model.ModelId;
import ch.ibw.appl.bimap.server.shared.service.Repository;

import java.util.List;

interface AngebotRepository<T extends Angebot, Angebot> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  T findByDescription(String description);

}
