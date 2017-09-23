package pl.harpi.samples.j2ee.demo.common.domain;

public interface Command<R, C extends CommandContext> {
    <R> R execute(C commandContext) throws ApplicationException;
}
