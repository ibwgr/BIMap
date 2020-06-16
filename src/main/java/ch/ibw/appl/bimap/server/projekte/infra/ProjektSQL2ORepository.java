package ch.ibw.appl.bimap.server.projekte.infra;

import ch.ibw.appl.bimap.server.projekte.model.Projekt;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProjektSQL2ORepository {

  private final Sql2o sql2o;

  public ProjektSQL2ORepository(boolean isTest) {
    if(isTest){
      sql2o = new Sql2o("jdbc:hsqldb:mem:bimap", "SAS", "sas123");
      try(Connection conn = sql2o.open()){
        executeFile(conn, "src/main/resources/META-INF/CreateTables.sql");
        executeFile(conn, "src/main/resources/META-INF/InsertData.sql");
      }
    }else{
      sql2o = new Sql2o("jdbc:mysql://localhost:3306/bimap", "root", "");
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

  public List<Projekt> all() {
    try(Connection conn = sql2o.open()){
      List<Projekt> projekts = conn.createQuery("SELECT * FROM projekt").executeAndFetch(Projekt.class);
      for (Projekt projekt : projekts) {
        int projektID = projekt.idprojekt;
        int ortID = projekt.ortid;
        int bauherrID = projekt.bauherrid;
        List<Projekt> ort = conn.createQuery("SELECT ort, plz FROM ort WHERE idort = :ortid").addParameter("ortid",ortID).executeAndFetch(Projekt.class);
        projekt.ort = ort.get(0).ort;
        projekt.plz = ort.get(0).plz;;
        List<String> bauherr = conn.createQuery("SELECT bauherr FROM bauherr where idbauherr =" + bauherrID).executeAndFetch(String.class);
        projekt.bauherr = bauherr.get(0);

        List<Integer> bauartIds = conn.createQuery("SELECT bauartid FROM bauartprojekt WHERE projektid = :projektid").addParameter("projektid",projektID).executeAndFetch(Integer.class);
        String bauartenString = "";
        for (int bauartid : bauartIds) {
          List<String> bauarten = conn.createQuery("SELECT bauart FROM bauart WHERE idbauart = :bauartid").addParameter("bauartid",bauartid).executeAndFetch(String.class);
          for (String bauart : bauarten) {
            bauartenString += bauart + " ";
          }
        }
        projekt.bauart = bauartenString;

        List<Integer> pvids = conn.createQuery("SELECT projektverfasserid FROM projektverfasserprojekt WHERE projektid = :projektid").addParameter("projektid",projektID).executeAndFetch(Integer.class);
        String pvString = "";
        for (int pvid : pvids) {
          List<String> pvs = conn.createQuery("SELECT projektverfasser FROM projektverfasser WHERE idprojektverfasser = :pvid").addParameter("pvid",pvid).executeAndFetch(String.class);
          for (String pv : pvs) {
            pvString += pv + " ";
          }
        }
        projekt.projektverfasser = pvString;

        List<Integer> leistungids = conn.createQuery("SELECT leistungid FROM leistungprojekt WHERE projektid = :projektid").addParameter("projektid",projektID).executeAndFetch(Integer.class);
        String leistungString = "";
        for (int leistungid : leistungids) {
          List<String> leistungen = conn.createQuery("SELECT leistungen FROM leistung WHERE idleistung = :leistungid").addParameter("leistungid",leistungid).executeAndFetch(String.class);
          for (String leistung : leistungen) {
            leistungString += leistung + " ";
          }
        }
        projekt.leistungen = pvString;
      }
      return projekts;
    }
  }


  public Projekt get(int id) {
    List<Projekt> projekte = all();
    for (Projekt projekt : projekte){
      if (projekt.idprojekt == id) {
        return projekt;
      }
    }
    return null;
  }

  public Projekt findByDescription(String description) {
    return null;
  }
}
