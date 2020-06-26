package ch.ibw.appl.bimap.server;

import ch.ibw.appl.bimap.server.shared.infra.HttpServer;
import java.util.Arrays;
import java.util.Optional;

public class Main {

  public static void main(String[] args) {
    /******* Fill in your SQL Username and Password here*********/
    String username = "root";
    String password = "";

    String httpPort =  getCLIArgument(args, "server.port", "2567");
    Boolean isTest = Boolean.parseBoolean(getCLIArgument(args, "test", "false"));

    new HttpServer(httpPort, isTest, username, password).start();
  }

  static String getCLIArgument(String[] args, String name, String defaultValue){
    Optional<String> argument = Arrays.stream(Optional.ofNullable(args).orElse(new String[]{})).filter(a -> a.startsWith("--"+name+"=")).findFirst();
    if(argument.isPresent()){
      return argument.get().split("=")[1];
    }else{
      return defaultValue;
    }
  }
}