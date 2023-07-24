/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: ConsoleHostBuilder.java
 * Last Update: 7/24/23, 4:56 PM
 */

package host;

import host.exec.IExecutableProvider;
import host.parser.DefaultCommandParser;
import host.parser.ICommandParser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

public class ConsoleHostBuilder {
    private IExecutableProvider provider = null;
    private Logger logger = null;
    private ICommandParser parser = new DefaultCommandParser();
    private InputStream input = System.in;
    private PrintStream output = System.out;

    public ConsoleHostBuilder setProvider(IExecutableProvider provider) {
        this.provider = provider;
        return this;
    }

    public ConsoleHostBuilder setLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public ConsoleHostBuilder setParser(ICommandParser parser) {
        this.parser = parser;
        return this;
    }

    public ConsoleHostBuilder setInput(InputStream input) {
        this.input = input;
        return this;
    }

    public ConsoleHostBuilder setOutput(PrintStream output) {
        this.output = output;
        return this;
    }

    public ConsoleHost build() {
        return new ConsoleHost(input, output, parser, provider, logger);
    }
}