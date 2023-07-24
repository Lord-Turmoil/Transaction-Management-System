package tms.host.exec;

public interface IExecutableProvider {
    IExecutable resolve(String name);
}
