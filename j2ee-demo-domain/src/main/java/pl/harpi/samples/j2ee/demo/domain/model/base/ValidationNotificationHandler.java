package pl.harpi.samples.j2ee.demo.domain.model.base;

public interface ValidationNotificationHandler {
    void handleError(String message);

    void handleError(String message, Object object);

    void handleInfo(String message);

    void handleInfo(String message, Object object);

    void handleWarning(String message);

    void handleWarning(String message, Object object);
}
