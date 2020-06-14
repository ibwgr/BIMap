package ch.ibw.appl.apiedi.server.leistungprojekt.infra;

import ch.ibw.appl.apiedi.server.leistungprojekt.model.ModelId;
import ch.ibw.appl.apiedi.server.leistungprojekt.model.Leistungprojekt;
import ch.ibw.appl.apiedi.server.leistungprojekt.service.LeistungprojektRepository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LeistungprojektSQL2ORepository implements LeistungprojektRepository<Leistungprojekt> {

  private final Sql2o sql2o;

  public LeistungprojektSQL2ORepository(boolean isTest) {
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
  public List<Leistungprojekt> all() {
    try(Connection conn = sql2o.open()){
      return conn.createQuery("select leistungid from leistungprojekt where projektid = <projektid>\"" +
              "\"select leistung from leistung where leistungid = <leistungid>").executeAndFetch(Leistungprojekt.class);
    }
  }

  @Override
  public ModelId add(Leistungprojekt leistungprojekt) {
    try(Connection conn = sql2o.open()){
      Query preparedStatement = conn.createQuery("insert into angebot (behart, betrag) values (:behart, :betrag)", true).bind(leistungprojekt);
      int newId = Integer.parseInt(preparedStatement.executeUpdate().getKey().toString());
      System.out.println(newId);
      return ModelId.create(newId);
    }
  }

  @Override
  public Leistungprojekt get(int id) {
    List<Leistungprojekt> leistungprojekte = all();
    for (Leistungprojekt leistungprojekt : leistungprojekte){
      if (leistungprojekt.id == id) {
        return leistungprojekt;
      }
    }
    return null;
  }

  @Override
  public Leistungprojekt findByDescription(String description) {
    return null;
  }

}
