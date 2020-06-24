package ch.ibw.appl.bimap.server.bauart.infra;

import ch.ibw.appl.bimap.server.bauart.model.Bauart;


import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BauartSQL2ORepository {

    private final Sql2o sql2o;

    public BauartSQL2ORepository(boolean isTest) {
        if (isTest) {
            sql2o = new Sql2o("jdbc:hsqldb:mem:bimap", "SAS", "sas123");
            try (Connection conn = sql2o.open()) {
                executeFile(conn, "src/main/resources/META-INF/CreateTables.sql");
                executeFile(conn, "src/main/resources/META-INF/InsertData.sql");
            }
        } else {
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
        for (String line : content.split(";")) {
            if (!line.trim().isEmpty()) {
                conn.createQuery(line).executeUpdate();
            }
        }
    }

    public List<Bauart> all() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from bauart").executeAndFetch(Bauart.class);
        }
    }

//  @Override
//  public ModelId add(Bauart bauart) {
//    try(Connection conn = sql2o.open()){
//      Query preparedStatement = conn.createQuery("insert into bauart", true).bind(bauart);
//      int newId = Integer.parseInt(preparedStatement.executeUpdate().getKey().toString());
//      System.out.println(newId);
//      return ModelId.create(newId);
//    }
//  }

    public Bauart get(int id) {
        List<Bauart> bauarten = all();
        for (Bauart bauart : bauarten) {
            if (bauart.idbauart == id) {
                return bauart;
            }
        }
        return null;
    }

    public Bauart findByDescription(String description) {
        return null;
    }
}
