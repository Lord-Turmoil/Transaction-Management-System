/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: ConsoleHost.java
 * Last Update: 7/24/23, 4:56 PM
 */

package host;

import host.exec.ExecutionException;
import host.exec.IExecutableProvider;
import host.exec.TerminationException;
import host.parser.ICommandParser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleHost {
    private final Scanner scanner;
    private final PrintStream printer;
    private final ICommandParser parser;
    private final IExecutableProvider provider;
    private final Logger logger;

    ConsoleHost(InputStream input,
                PrintStream output,
                ICommandParser parser,
                IExecutableProvider provider, Logger logger) {
        this.scanner = new Scanner(input);
        this.printer = output;
        this.parser = parser;
        this.provider = provider;
        this.logger = logger;
    }

    public void run() {
        if (provider == null) {
            logger.severe("Missing executable provider");
            return;
        }

        while (scanner.hasNextLine()) {
            var args = parser.Parse(scanner.nextLine());
            if (args.isEmpty()) {
                continue;
            }

            try {
                if (logger != null) {
                    logger.log(Level.INFO, args.get(0), args);
                }
                var executable = provider.resolve(args.get(0));
                if (executable == null) {
                    throw new ExecutionException("Command '" + args.get(0) + "' not found");
                }
                args.remove(0);
                executable.execute(args);
            } catch (TerminationException e) {
                if (logger != null) {
                    logger.log(Level.WARNING, e.getMessage(), e);
                }
                return;
            } catch (ExecutionException e) {
                printer.println(e.getMessage());
                if (logger != null) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        }
    }
}
