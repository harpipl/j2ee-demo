package pl.harpi.samples.j2ee.demo.api.base.service;

import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;

public interface Command<R, C extends CommandContext> {
    <R> R execute(C commandContext) throws ApplicationException;
}
