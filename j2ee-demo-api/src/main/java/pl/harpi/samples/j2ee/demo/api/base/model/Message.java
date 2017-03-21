package pl.harpi.samples.j2ee.demo.api.base.model;

public class Message {
    private MessageType type;
    private String message;
    private Object object;

    public Message(MessageType type, String message, Object object) {
        this.setType(type);
        this.setMessage(message);
        this.setObject(object);
    }

    public Message(MessageType type, String message) {
        this(type, message, null);
    }

    public MessageType getType() {
        return type;
    }

    private void setType(MessageType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

}
