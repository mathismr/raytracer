import com.raytracer.core.scene.Scene;
import com.raytracer.core.scene.SceneFileParser;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class SceneFileParserTest {
    List<Path> getTestFiles(String resourcePath) throws IOException {
        return Files.walk(Paths.get(resourcePath))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".scene"))
                .collect(Collectors.toList());
    }

    @Test
    void parse() throws IOException {
        List<Path> testFiles = getTestFiles("src/main/resources/scenes/jalon2/");
        SceneFileParser parser = new SceneFileParser();

        for (Path testFile : testFiles) {
            Scene scene = parser.parse(testFile.toString());
            System.out.println(scene.toString());
        }
    }
}