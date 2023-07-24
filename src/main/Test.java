import host.ConsoleHost;
import ioc.Container;
import tms.Startup;

public class Test {
    public static void main(String[] args) {
        Startup.configureIoC();
        
        var container = Container.getGlobal();

        var host = container.resolveRequired(ConsoleHost.class);
        host.run();
    }
}