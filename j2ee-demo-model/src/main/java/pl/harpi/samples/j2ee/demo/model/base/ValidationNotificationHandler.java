package pl.harpi.samples.j2ee.demo.model.base;

import pl.harpi.samples.j2ee.demo.api.base.model.Message;

import java.util.List;

public interface ValidationNotificationHandler<MESSAGE> {
    List<MESSAGE> getMessages();

    void handleError(String message);

    void handleError(String message, Object object);

    void handleInfo(String message);

    void handleInfo(String message, Object object);

    void handleWarning(String message);

    void handleWarning(String message, Object object);

    boolean hasErrors();
}
