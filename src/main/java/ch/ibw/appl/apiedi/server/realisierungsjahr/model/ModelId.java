package ch.ibw.appl.apiedi.server.realisierungsjahr.model;

public class ModelId {

    public int id;

    public static ModelId create(int id) {
        ModelId model = new ModelId();
        model.id = id;
        return model;
    }
}
