package ch.ibw.appl.bimap.server.shared.service;

import ch.ibw.appl.bimap.server.bauart.model.ModelId;

import java.util.List;

public interface Repository<T> {
  List<T> all();
  ModelId add(T obj);
  T get(int id);
}
