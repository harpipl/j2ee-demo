package pl.harpi.samples.j2ee.demo.common.domain;

public class ApplicationException extends Exception {
    private final transient Object object;

    public ApplicationException(String message, Object object) {
        super(message);
        this.object = object;
    }

    public ApplicationException(String message) {
        this(message, null);
    }

    public ApplicationException(Object object) {
        this(null, object);
    }

    public Object getObject() {
        return object;
    }
}
