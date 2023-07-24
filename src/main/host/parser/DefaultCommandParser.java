package host.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DefaultCommandParser implements ICommandParser {
    @Override
    public List<String> Parse(String line) {
        ArrayList<String> args = new ArrayList<>();
        try (Scanner scanner = new Scanner(line)) {
            while (scanner.hasNext()) {
                args.add(scanner.next());
            }
        }
        return args;
    }
}
