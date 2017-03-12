package pl.harpi.samples.j2ee.demo.api.exceptions;

public class ApplicationException extends Exception {
    ApplicationException(String message) {
        super(message);
    }
}
