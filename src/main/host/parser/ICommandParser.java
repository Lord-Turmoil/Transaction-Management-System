package host.parser;

import java.util.List;

public interface ICommandParser {
    List<String> Parse(String line);
}
