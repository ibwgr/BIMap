package ch.ibw.appl.bimap.server.leistung.infra;

import ch.ibw.appl.bimap.server.leistung.model.Leistung;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LeistungSQL2ORepository{

  private final Sql2o sql2o;

  public LeistungSQL2ORepository(boolean isTest) {
    if(isTest){
      sql2o = new Sql2o("jdbc:hsqldb:mem:bimap", "SAS", "sas123");
      try(Connection conn = sql2o.open()){
        executeFile(conn, "src/main/resources/META-INF/CreateTables.sql");
        executeFile(conn, "src/main/resources/META-INF/InsertData.sql");
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

  public List<Leistung> all() {
    try(Connection conn = sql2o.open()){
      return conn.createQuery("select * from leistung").executeAndFetch(Leistung.class);
    }
  }

//  public ModelId add(Leistung leistungprojekt) {
//    try(Connection conn = sql2o.open()){
//      Query preparedStatement = conn.createQuery("insert into angebot (behart, betrag) values (:behart, :betrag)", true).bind(leistungprojekt);
//      int newId = Integer.parseInt(preparedStatement.executeUpdate().getKey().toString());
//      System.out.println(newId);
//      return ModelId.create(newId);
//    }
//  }

  public Leistung get(int id) {
    List<Leistung> leistungen = all();
    for (Leistung leistung : leistungen){
      if (leistung.idleistung == id) {
        return leistung;
      }
    }
    return null;
  }

  public Leistung findByDescription(String description) {
    return null;
  }
}
