package ch.ibw.appl.bimap.server.functional;

import ch.ibw.appl.bimap.server.functional.shared.FunctionalTest;
import ch.ibw.appl.bimap.server.projekte.model.Projekt;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProjektTest extends FunctionalTest {

  @Test
  public void notAcceptable() throws HttpClientException {
    GetMethod method = httpClient.get("/projekte", false);
    HttpResponse response = httpClient.execute(method);
    String body = new String(response.body());
    System.out.println(body);

//    assertEquals(HttpStatus.NOT_ACCEPTABLE_406, response.code());
    assertEquals(HttpStatus.NOT_ACCEPTABLE_406, response.code());
  }

  @Test
  public void get_projekte_returnValidAnswer() {
    HttpResponse response = executeGet("/projekte");

    assertEquals(HttpStatus.OK_200, response.code());
    assertEquals("application/json", response.headers().get("Content-Type").get(0));

    String body = new String(response.body());

    List<Projekt> projekte = new JSONSerializer().deserialize(body, new TypeReference<List<Projekt>>() {});
    assertEquals(Projekt.create(1, 0502,"Kindergarten Taescherloch",1,2759543, 1220275,2009,286000, 1).projektname, projekte.get(0).projektname);
  }

  @Test
  public void get_byId() {
    HttpResponse response = executeGet("/projekte/1");

    assertEquals(HttpStatus.OK_200, response.code());
    assertEquals("application/json", response.headers().get("Content-Type").get(0));

    String body = new String(response.body());

    Projekt projekte = new JSONSerializer().deserialize(body, new TypeReference<Projekt>() {});
    assertEquals(Projekt.create(1, 0502,"Kindergarten Taescherloch",1,2759543, 1220275,2009,286000, 1).projektname, projekte.projektname);
    System.out.println(projekte.bauherr);
  }

  //Querey-Test

  @Test
  public void get_byQuery() {
    HttpResponse response = executeGet("/projekte?&bauherr=Gemeinde%20Triesenberg&bauart=Öffentlicher%20Bau&realisiserungsjahr=2010");
    assertEquals(HttpStatus.OK_200, response.code());
    assertEquals("application/json", response.headers().get("Content-Type").get(0));

    String body = new String(response.body());
    System.out.println(body);

    Projekt[] projekte = new JSONSerializer().deserialize(body, new TypeReference<Projekt[]>() {});
    assertEquals(Projekt.create(1, 0502,"Kindergarten Taescherloch",1,2759543, 1220275,2009,286000, 1).koordx, projekte[0].koordx);
  }



  @Test
  public void get_byId_notFound() {
    HttpResponse response = executeGet("/projekte/22");
    String body = new String(response.body());

    assertEquals(HttpStatus.NOT_FOUND_404, response.code());
    assertEquals("application/json", response.headers().get("Content-Type").get(0));

    assertEquals("", body);
  }
}
