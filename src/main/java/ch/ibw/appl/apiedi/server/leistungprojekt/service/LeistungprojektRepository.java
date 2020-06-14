package ch.ibw.appl.apiedi.server.leistungprojekt.service;

import ch.ibw.appl.apiedi.server.leistungprojekt.model.Leistungprojekt;
import ch.ibw.appl.apiedi.server.leistungprojekt.model.ModelId;
import ch.ibw.appl.apiedi.server.shared.service.Repository;

import java.util.List;

public interface LeistungprojektRepository<T extends Leistungprojekt> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  T findByDescription(String description);
}
