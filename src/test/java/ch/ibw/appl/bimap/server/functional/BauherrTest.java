package ch.ibw.appl.bimap.server.functional;

import ch.ibw.appl.bimap.server.bauaherr.model.Bauherr;
import ch.ibw.appl.bimap.server.bauart.model.Bauart;
import ch.ibw.appl.bimap.server.functional.shared.FunctionalTest;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BauherrTest extends FunctionalTest {

  @Test
  public void notAcceptable() throws HttpClientException {
    GetMethod method = httpClient.get("/bauherren", false);
    HttpResponse response = httpClient.execute(method);
    String body = new String(response.body());

//    assertEquals(HttpStatus.NOT_ACCEPTABLE_406, response.code());
    assertEquals(HttpStatus.NOT_ACCEPTABLE_406, response.code());
  }

  @Test
  public void get_bauherren_returnValidAnswer() {
    HttpResponse response = executeGet("/bauherren");

    assertEquals(HttpStatus.OK_200, response.code());
    assertEquals("application/json", response.headers().get("Content-Type").get(0));

    String body = new String(response.body());

    List<Bauherr> bauherren = new JSONSerializer().deserialize(body, new TypeReference<List<Bauherr>>() {});
    assertEquals(Bauherr.create(1, "Gemeinde Triesenberg").bauherr, bauherren.get(0).bauherr);
  }

  @Test
  public void get_byId() {
    HttpResponse response = executeGet("/bauherr/1");

    assertEquals(HttpStatus.OK_200, response.code());
    assertEquals("application/json", response.headers().get("Content-Type").get(0));

    String body = new String(response.body());

    Bauherr bauherren = new JSONSerializer().deserialize(body, new TypeReference<Bauherr>() {});
    assertEquals(Bauherr.create(1, "Gemeinde Triesenberg").bauherr, bauherren.bauherr);
  }

  @Test
  public void get_byId_notFound() {
    HttpResponse response = executeGet("/bauherr/12");
    String body = new String(response.body());

    assertEquals(HttpStatus.NOT_FOUND_404, response.code());
    assertEquals("application/json", response.headers().get("Content-Type").get(0));

    assertEquals("", body);
  }
}
