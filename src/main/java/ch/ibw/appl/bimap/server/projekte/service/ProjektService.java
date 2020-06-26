package ch.ibw.appl.bimap.server.projekte.service;

import ch.ibw.appl.bimap.server.projekte.infra.ProjektSQL2ORepository;
import ch.ibw.appl.bimap.server.projekte.model.Projekt;

import java.util.List;

public class ProjektService {
    private final ProjektSQL2ORepository projektRepo;

    public ProjektService(ProjektSQL2ORepository projektRepo) {
        this.projektRepo = projektRepo;
    }

    public List<Projekt> all() {
        return projektRepo.all();
    }

    public Projekt getById(int id) {
        return projektRepo.get(id);
    }

    public List<Projekt> getByFilter(String bauherr, String bauart, int realisierungsjahr) {
        return projektRepo.getByFilter(bauherr, bauart, realisierungsjahr);
    }
}
