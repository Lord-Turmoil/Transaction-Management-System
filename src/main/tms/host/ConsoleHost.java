package tms.host;

import tms.host.exec.ExecutionException;
import tms.host.exec.IExecutableProvider;
import tms.host.exec.TerminationException;
import tms.host.parser.DefaultCommandParser;
import tms.host.parser.ICommandParser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleHost {
    private final Scanner scanner;
    private final PrintStream printer;
    private final ICommandParser parser;
    private final IExecutableProvider provider;

    public ConsoleHost(IExecutableProvider provider) {
        this(new DefaultCommandParser(), provider);
    }

    public ConsoleHost(ICommandParser parser, IExecutableProvider provider) {
        this(System.in, System.out, parser, provider);
    }

    public ConsoleHost(InputStream input,
                       PrintStream output,
                       ICommandParser parser,
                       IExecutableProvider provider) {
        this.scanner = new Scanner(input);
        this.printer = output;
        this.parser = parser;
        this.provider = provider;
    }

    public void run() {
        while (scanner.hasNextLine()) {
            var args = parser.Parse(scanner.nextLine());
            if (args.isEmpty()) {
                continue;
            }

            try {
                var executable = provider.resolve(args.get(0));
                args.remove(0);
                executable.execute(args);
            } catch (TerminationException e) {
                return;
            } catch (ExecutionException e) {
                printer.println(e.getMessage());
            }
        }
    }
}
