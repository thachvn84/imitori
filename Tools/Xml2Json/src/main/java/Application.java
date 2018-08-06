import org.json.JSONObject;
import org.json.XML;
import javax.json.JsonException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Application {
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static String TEST_XML_STRING =
            "<?xml version=\"1.0\" ?><test attrib=\"moretest\">Turn this to JSON</test>";

    private static String readLineByLineJava8(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    public static void main(String[] args) {
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(readLineByLineJava8("JMdict_e.xml"));
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            try (PrintStream out = new PrintStream(new FileOutputStream("filename.txt"))) {
                out.print(jsonPrettyPrintString);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(jsonPrettyPrintString);
        } catch (JsonException je) {
            System.out.println(je.toString());
        }
    }
}