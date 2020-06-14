package ch.ibw.appl.apiedi.server.projekte.infra;

import ch.ibw.appl.apiedi.server.projekte.model.ModelId;
import ch.ibw.appl.apiedi.server.projekte.model.Projekt;
import ch.ibw.appl.apiedi.server.projekte.service.ProjektRepository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProjektSQL2ORepository implements ProjektRepository<Projekt> {

  private final Sql2o sql2o;

  public ProjektSQL2ORepository(boolean isTest) {
    if(isTest){
      sql2o = new Sql2o("jdbc:hsqldb:mem:apiedi", "SAS", "sas123");
      try(Connection conn = sql2o.open()){
        executeFile(conn, "C:\\Users\\mvink\\Documents\\Applikations-Entwicklung\\Privat\\A-PiediApp\\apiedi-java-server\\src\\main\\resources\\META-INF\\createtableAngebot.sql");
      }
    }else{
      sql2o = new Sql2o("jdbc:mysql://localhost:3306/apiedi", "SAS", "sas123");
    }
  }

  private void executeFile(Connection conn, String s) {
    String path = s;
    String content;
    try {
      content = new String(Files.readAllBytes(Paths.get(path)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    for(String line : content.split(";")){
      if(!line.trim().isEmpty()){
        conn.createQuery(line).executeUpdate();
      }
    }
  }

  @Override
  public List<Projekt> all() {
    try(Connection conn = sql2o.open()){
      return conn.createQuery("select * from angebot").executeAndFetch(Projekt.class);
    }
  }

  @Override
  public ch.ibw.appl.apiedi.server.bauart.model.ModelId add(Projekt projekt) {
    try(Connection conn = sql2o.open()){
      Query preparedStatement = conn.createQuery("insert into angebot (behart, betrag) values (:behart, :betrag)", true).bind(projekt);
      int newId = Integer.parseInt(preparedStatement.executeUpdate().getKey().toString());
      System.out.println(newId);
      return ModelId.create(newId);
    }
  }

  @Override
  public Projekt get(int id) {
    List<Projekt> projekte = all();
    for (Projekt projekt : projekte){
      if (projekt.id == id) {
        return projekt;
      }
    }
    return null;
  }

  @Override
  public Projekt findByDescription(String description) {
    return null;
  }
}
