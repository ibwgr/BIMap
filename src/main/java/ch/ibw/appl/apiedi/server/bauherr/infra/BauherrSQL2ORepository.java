package ch.ibw.appl.apiedi.server.bauherr.infra;

import ch.ibw.appl.apiedi.server.bauherr.model.Bauherr;
import ch.ibw.appl.apiedi.server.bauart.model.ModelId;
import ch.ibw.appl.apiedi.server.bauherr.service.BauherrRepository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BauherrSQL2ORepository implements BauherrRepository<Bauherr> {

  private final Sql2o sql2o;

  public BauherrSQL2ORepository(boolean isTest) {
    if(isTest){
      sql2o = new Sql2o("jdbc:hsqldb:mem:bimap", "SAS", "sas123");
      try(Connection conn = sql2o.open()){
        executeFile(conn, "C:\\Users\\mvink\\Documents\\Applikations-Entwicklung\\Privat\\A-PiediApp\\apiedi-java-server\\src\\main\\resources\\META-INF\\createtableAngebot.sql");
      }
    }else{
      sql2o = new Sql2o("jdbc:mysql://localhost:3306/bimap", "SAS", "sas123");
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
  public List<Bauherr> all() {
    try(Connection conn = sql2o.open()){
      return conn.createQuery("select bauherr from bauehrr").executeAndFetch(Bauherr.class);
    }
  }

  @Override
  public ModelId add(Bauherr bauherr) {
    try(Connection conn = sql2o.open()){
      Query preparedStatement = conn.createQuery("insert into bauherr", true).bind(bauherr);
      int newId = Integer.parseInt(preparedStatement.executeUpdate().getKey().toString());
      System.out.println(newId);
      return ModelId.create(newId);
    }
  }

  @Override
  public Bauherr get(int id) {
    List<Bauherr> bauherren = all();
    for (Bauherr bauherr : bauherren){
      if (bauherr.id == id) {
        return bauherr;
      }
    }
    return null;
  }

  @Override
  public Bauherr findByDescription(String description) {
    return null;
  }

}
