package ch.ibw.appl.bimap.server.functional;

import ch.ibw.appl.bimap.server.bauart.model.Bauart;
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

import static org.junit.Assert.*;

public class BauartTest extends FunctionalTest {

  @Test
  public void notAcceptable() throws HttpClientException {
<<<<<<< Updated upstream
    //GetMethod method = httpClient.get("/bauarten", false);
    HttpResponse response = executeGet("/projekte");
=======
    GetMethod method = httpClient.get("/bauarten", false);
    HttpResponse response = httpClient.execute(method);
>>>>>>> Stashed changes
    String body = new String(response.body());
    System.out.println(response);

//    assertEquals(HttpStatus.NOT_ACCEPTABLE_406, response.code());
    assertEquals(HttpStatus.NOT_ACCEPTABLE_406, response.code());
  }

  @Test
<<<<<<< Updated upstream
  public void get_angebote() {
    HttpResponse response = executeGet("/bauarten");
//    HttpResponse response = executeGet("/todo/items", "text/csv");

    assertEquals(HttpStatus.OK_200, response.code());
    assertEquals("application/json", response.headers().get("Content-Type").get(0));

=======
  public void get_bauarten() throws HttpClientException {
    HttpResponse response = executeGet("/bauarten");
>>>>>>> Stashed changes
    String body = new String(response.body());
    System.out.println(response);

    List<Bauart> behandlungen = new JSONSerializer().deserialize(body, new TypeReference<List<Bauart>>() {});
    //assertEquals(Bauart.create(0, "Einfamilienhaus"), bauarten.get(0));
  }

//  @Test
//  public void get_byId() {
//    HttpResponse response = executeGet("/angebote/2");
//
//    assertEquals(HttpStatus.OK_200, response.code());
//    assertEquals("application/json", response.headers().get("Content-Type").get(0));
//
//    String body = new String(response.body());
//
//    ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt angebot = new JSONSerializer().deserialize(body, new TypeReference<ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt>() {});
//    assertEquals(new Float(85), angebot.betrag, 0);
//  }
//
//  @Test
//  public void get_byId_notFound() {
//    HttpResponse response = executeGet("/angebote/4");
//
//    assertEquals(HttpStatus.NOT_FOUND_404, response.code());
//    assertEquals("application/json", response.headers().get("Content-Type").get(0));
//
//    String body = new String(response.body());
//    assertEquals("", body);
//  }
//
//  @Test
//  public void create_angebot() {
//    ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt angebot = ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt.create("Extra", 110);
//    HttpResponse response = executePost("/angebote", angebot);
//
//    assertEquals(HttpStatus.CREATED_201, response.code());
//    assertEquals("application/json", response.headers().get("Content-Type").get(0));
//
//    String body = new String(response.body());
//    ModelId id = new JSONSerializer().deserialize(body, new TypeReference<ModelId>() {});
//    assertNotNull(id);
//
//
//    HttpResponse response2 = executeGet("/angebote/" + id.id);
//
//    assertEquals(HttpStatus.OK_200, response2.code());
//    assertEquals("application/json", response.headers().get("Content-Type").get(0));
//
//    String body2 = new String(response2.body());
//    ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt angebot2 = new JSONSerializer().deserialize(body2, new TypeReference<ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt>() {});
//    assertEquals(new Float(110), angebot2.betrag, 0);
//  }
//
//  @Test
//  public void create_angebot_validationFailed() {
//    Object angebot = ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt.create("", 0);
//    HttpResponse response = executePost("/angebote", angebot);
//
//    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY_422, response.code());
//    assertEquals("application/json", response.headers().get("Content-Type").get(0));
//
//    String body = new String(response.body());
//    System.out.println(body);
//    assertTrue(body.contains("message"));
//  }
}
