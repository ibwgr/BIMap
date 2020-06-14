package ch.ibw.appl.apiedi.server.realisierungsjahr.infra;

import ch.ibw.appl.apiedi.server.realisierungsjahr.model.ModelId;
import ch.ibw.appl.apiedi.server.realisierungsjahr.model.Realisierungsjahr;
import ch.ibw.appl.apiedi.server.realisierungsjahr.service.RealisierungsjahrRepository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RealisierungsjahrSQL2ORepository implements RealisierungsjahrRepository<Realisierungsjahr> {

  private final Sql2o sql2o;

  public RealisierungsjahrSQL2ORepository(boolean isTest) {
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
  public List<Realisierungsjahr> all() {
    try(Connection conn = sql2o.open()){
      return conn.createQuery("select * from realisierungsjahr").executeAndFetch(Realisierungsjahr.class);
    }
  }

  @Override
  public ModelId add(Realisierungsjahr realisierungsjahr) {
    try(Connection conn = sql2o.open()){
      Query preparedStatement = conn.createQuery("insert into angebot (behart, betrag) values (:behart, :betrag)", true).bind(realisierungsjahr);
      int newId = Integer.parseInt(preparedStatement.executeUpdate().getKey().toString());
      System.out.println(newId);
      return ModelId.create(newId);
    }
  }

  public Realisierungsjahr get(int id) {
    List<Realisierungsjahr> realisierungsjahre = all();
    for (Realisierungsjahr realisierungsjahr : realisierungsjahre){
      if (realisierungsjahr.id == id) {
        return realisierungsjahr;
      }
    }
    return null;
  }

  @Override
  public Realisierungsjahr findByDescription(String description) {
    return null;
  }

}
