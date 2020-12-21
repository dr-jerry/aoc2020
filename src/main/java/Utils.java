import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static List<Integer> readIntLinesForDay(String s) throws IOException {
        return readLinesForDay(s).map(n -> Integer.valueOf(n)).collect(Collectors.toList());
    }

    public static List<Long> readLongLinesForDay(String s) throws IOException {
        return readLinesForDay(s).map(n -> Long.valueOf(n)).collect(Collectors.toList());
    }

    public static List<String> readStringLinesForDay(String file) throws IOException {
        return readLinesForDay(file).collect(Collectors.toList());
    }

    private static Stream<String> readLinesForDay(String file) throws IOException {
        return Files.lines(readFileForDay(file));
    }

    private static Path readFileForDay(String fileName) {
        return Paths.get(fileName);
    }

    public static boolean isValidPattern(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).matches();
    }

}