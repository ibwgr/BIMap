package ch.ibw.appl.bimap.server.functional.shared;

import ch.ibw.appl.bimap.server.shared.infra.HttpServer;
import ch.ibw.appl.bimap.server.shared.service.JSONSerializer;
import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import org.junit.Rule;
import spark.servlet.SparkApplication;

public class FunctionalTest {

  public static class BIMapApp implements SparkApplication {
    private HttpServer httpServer;

    @Override
    public void init() {
<<<<<<< Updated upstream
      httpServer = new HttpServer("4567", true);
=======
      httpServer = new HttpServer("3567", true);
>>>>>>> Stashed changes
      httpServer.start();
    }

    @Override
    public void destroy() {
      httpServer.stop();
    }
  }

  @Rule
  public SparkServer<BIMapApp> httpClient = new SparkServer<>(BIMapApp.class);

  public HttpResponse executeGet(String path, String acceptType){
    GetMethod method = httpClient.get(path, false);
    method.addHeader("Accept", acceptType);
    try {
      return httpClient.execute(method);
    } catch (HttpClientException e) {
      throw new RuntimeException(e);
    }
  }

  public HttpResponse executeGet(String path) {
    return executeGet(path, "application/json");
  }

  public HttpResponse executePost(String path, Object body){
    return executePost(path, body, "application/json");
  }

  public HttpResponse executePost(String path, Object body, String acceptType) {
    PostMethod method = httpClient.post(path, new JSONSerializer().serialize(body),false);
    method.addHeader("Accept", acceptType);
    try {
      return httpClient.execute(method);
    } catch (HttpClientException e) {
      throw new RuntimeException(e);
    }
  }
}
