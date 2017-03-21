package pl.harpi.samples.j2ee.demo.domain.model.base;

import pl.harpi.samples.j2ee.demo.api.base.model.Message;
import pl.harpi.samples.j2ee.demo.api.base.model.MessageType;
import pl.harpi.samples.j2ee.demo.model.base.ValidationNotificationHandler;

import java.util.ArrayList;
import java.util.List;

public class MessagesValidationNotificationHandler implements ValidationNotificationHandler {
    private boolean hasErrors = false;

    private List<Message> messages = new ArrayList<>();
    @Override
    public void handleError(String message, Object object) {
        hasErrors = true;
        messages.add(new Message(MessageType.ERROR, message, object));
    }

    @Override
    public void handleError(String message) {
        handleError(message, null);
    }

    @Override
    public void handleInfo(String message) {
        messages.add(new Message(MessageType.INFO, message));
    }

    @Override
    public void handleInfo(String message, Object object) {
        messages.add(new Message(MessageType.INFO, message, object));
    }

    @Override
    public void handleWarning(String message) {
        messages.add(new Message(MessageType.WARNING, message));
    }

    @Override
    public void handleWarning(String message, Object object) {
        messages.add(new Message(MessageType.WARNING, message, object));
    }

    public List<Message> getMessages() {
        return messages;
    }

    public boolean hasErrors() {
        return hasErrors;
    }
}
