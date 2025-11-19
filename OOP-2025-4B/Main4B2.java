import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import oop.ex4B2.*;

public class Main4B2 {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("usage: java Main4B2 <file>");
            return;
        }
        List<String> lines = Files.readAllLines(Paths.get(args[0]));
        Parser parser = new Parser();
        Program program = parser.parse(lines);
        int result = program.execute();
        System.out.println(result);
    }
}
