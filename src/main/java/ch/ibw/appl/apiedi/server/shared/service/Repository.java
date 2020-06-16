package ch.ibw.appl.apiedi.server.shared.service;

import ch.ibw.appl.apiedi.server.bauart.model.ModelId;

import java.util.List;

public interface Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
}
