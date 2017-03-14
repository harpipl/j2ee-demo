package pl.harpi.samples.j2ee.demo.model.base;

public abstract class Validator {
    private ValidationNotificationHandler notificationHandler;

    public Validator(ValidationNotificationHandler notificationHandler) {
        this.setNotificationHandler(notificationHandler);
    }

    public abstract void validate();

    protected ValidationNotificationHandler getNotificationHandler() {
        return notificationHandler;
    }

    private void setNotificationHandler(ValidationNotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }
}
