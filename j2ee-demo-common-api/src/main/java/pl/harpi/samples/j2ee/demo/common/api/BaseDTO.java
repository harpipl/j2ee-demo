package pl.harpi.samples.j2ee.demo.common.api;

public abstract class BaseDTO {
    private Long id;

    protected BaseDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
