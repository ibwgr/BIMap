package ch.ibw.appl.apiedi.server.angebot.infra;

import ch.ibw.appl.apiedi.server.behandlungen.model.ModelId;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AngebotSQL2ORepository implements ch.ibw.appl.apiedi.server.angebot.service.BauartRepository<ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt> {

  private final Sql2o sql2o;

  public AngebotSQL2ORepository(boolean isTest) {
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
  public List<T> all() {
    try(Connection conn = sql2o.open()){
      return conn.createQuery("select * from angebot").executeAndFetch(ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt.class);
    }
  }

  @Override
  public ModelId add(ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt angebot) {
    try(Connection conn = sql2o.open()){
      Query preparedStatement = conn.createQuery("insert into angebot (behart, betrag) values (:behart, :betrag)", true).bind(angebot);
      int newId = Integer.parseInt(preparedStatement.executeUpdate().getKey().toString());
      System.out.println(newId);
      return ModelId.create(newId);
    }
  }

  @Override
  public ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt get(int id) {
    List<ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt> angebote = all();
    for (ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt angebot : angebote){
      if (angebot.id == id) {
        return angebot;
      }
    }
    return null;
  }

  @Override
  public ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt findByDescription(String description) {
    return null;
  }

}
