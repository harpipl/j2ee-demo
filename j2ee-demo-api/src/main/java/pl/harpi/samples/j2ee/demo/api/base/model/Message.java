package pl.harpi.samples.j2ee.demo.api.base.model;

public class Message {
    private MessageType type;
    private String text;
    private Object object;

    public Message(MessageType type, String text, Object object) {
        this.setType(type);
        this.setText(text);
        this.setObject(object);
    }

    public Message(MessageType type, String text) {
        this(type, text, null);
    }

    public MessageType getType() {
        return type;
    }

    private void setType(MessageType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    private void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

}
