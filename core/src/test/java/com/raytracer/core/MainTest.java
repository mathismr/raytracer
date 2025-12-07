package com.raytracer.core;

import com.raytracer.core.imaging.Frame;
import com.raytracer.core.scene.RayTracer;
import com.raytracer.core.scene.Scene;
import com.raytracer.core.scene.SceneFileParser;
import com.raytracer.imgcompare.ImageComparator;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


class MainTest {
    List<Path> getTestFiles(String resourcePath) throws IOException {
        return Files.walk(Paths.get(resourcePath))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".test"))
                .collect(Collectors.toList());
    }

    void writeImage(BufferedImage image, String filename) throws IOException {
        Path path = Path.of(filename);
        if (path.getParent() != null && !Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        boolean success = ImageIO.write(image, "png", path.toFile());
        if (!success) {
            System.err.println("Error writing difference");
        }
    }

    void loopFiles(List<Path> testFiles) throws Exception {
        for (Path testFile : testFiles) {
            Scene scene = new SceneFileParser(testFile.toString()).parse();
            System.out.println(scene.toString());

            RayTracer rayTracer = new RayTracer(scene);
            Frame frame = new Frame(rayTracer);
            BufferedImage renderedImage = frame.render();
            writeImage(renderedImage, testFile.toString().replace(".test", "-render.png"));

            String pngFileName = testFile.toString().replace(".test", ".png");
            BufferedImage expectedImage = ImageIO.read(new File(pngFileName));

            ImageComparator comparator = new ImageComparator(renderedImage, expectedImage);
            BufferedImage difference = comparator.difference();
            writeImage(difference, testFile.toString().replace(".test", ".diff.png"));
        }
    }

    @Test
    void jalon3() throws Exception {
        String resourcePath = "src/main/resources/scenes/jalon3/";
        List<Path> testFiles = getTestFiles(resourcePath);

        loopFiles(testFiles);
    }

    @Test
    void jalon4() throws Exception {
        String resourcePath = "src/main/resources/scenes/jalon4/";
        List<Path> testFiles = getTestFiles(resourcePath);

        loopFiles(testFiles);
    }

    @Test
    void jalon5() throws Exception {
        String resourcePath = "src/main/resources/scenes/jalon5/";
        List<Path> testFiles = getTestFiles(resourcePath);

        loopFiles(testFiles);
    }

    @Test
    void jalon6() throws Exception {
        String resourcePath = "src/main/resources/scenes/jalon6/";
        List<Path> testFiles = getTestFiles(resourcePath);

        loopFiles(testFiles);
    }
}