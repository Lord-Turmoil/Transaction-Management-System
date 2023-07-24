package tms.cmd;

import host.exec.ExecutionException;
import host.exec.IExecutable;
import host.exec.TerminationException;

import java.util.List;
import java.util.logging.Logger;

public abstract class BaseCommand implements IExecutable {
    protected Logger logger = null;

    public BaseCommand(Logger logger) {
        this.logger = logger;
    }
}
