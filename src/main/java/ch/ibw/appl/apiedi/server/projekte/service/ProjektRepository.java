package ch.ibw.appl.apiedi.server.projekte.service;

import ch.ibw.appl.apiedi.server.bauart.model.ModelId;
import ch.ibw.appl.apiedi.server.projekte.model.Projekt;
import ch.ibw.appl.apiedi.server.shared.service.Repository;

import java.util.List;

public interface ProjektRepository<T extends Projekt> extends Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
  T findByDescription(String description);
}