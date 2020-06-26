package ch.ibw.appl.bimap.server.bauaherr.infra;

import ch.ibw.appl.bimap.server.bauaherr.model.Bauherr;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BauherrSQL2ORepository {

    private final Sql2o sql2o;

    public BauherrSQL2ORepository(boolean isTest, String username, String password) {
        if (isTest) {
            sql2o = new Sql2o("jdbc:hsqldb:mem:bimap", username, password);
            try (Connection conn = sql2o.open()) {
                executeFile(conn, "src/main/resources/META-INF/CreateTables.sql");
                executeFile(conn, "src/main/resources/META-INF/InsertData.sql");
            }
        } else {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/bimap", username, password);
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

    public List<Bauherr> all() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from bauherr").executeAndFetch(Bauherr.class);
        }
    }

    public Bauherr get(int id) {
        List<Bauherr> bauherren = all();
        for (Bauherr bauherr : bauherren) {
            if (bauherr.idbauherr == id) {
                return bauherr;
            }
        }
        return null;
    }
}
