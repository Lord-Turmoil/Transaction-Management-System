package tms.host.exec;

import java.util.List;

public interface IExecutable {
    void execute(List<String> args) throws ExecutionException, TerminationException;
}
