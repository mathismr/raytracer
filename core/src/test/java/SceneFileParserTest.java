import com.raytracer.core.scene.Scene;
import com.raytracer.core.scene.SceneFileParser;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class SceneFileParserTest {
    @Test
    void parse() throws FileNotFoundException {
        SceneFileParser parser = new SceneFileParser("src/main/resources/scenes/jalon2/test3.scene");
        Scene scene = parser.parse();
        System.out.println(scene.toString());
    }
}