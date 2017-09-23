package pl.harpi.samples.j2ee.demo.common.domain;

import java.util.List;

public interface ValidationNotificationHandler<M> {
    List<M> getMessages();

    void handleError(String message);

    void handleError(String message, Object object);

    void handleInfo(String message);

    void handleInfo(String message, Object object);

    void handleWarning(String message);

    void handleWarning(String message, Object object);

    boolean hasErrors();
}
