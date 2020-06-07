package ch.ibw.appl.apiedi.server.behandlungen.service;

public class ValidationError extends RuntimeException {
  public String message;

  public ValidationError(String message) {
    this.message = message;
  }
}
