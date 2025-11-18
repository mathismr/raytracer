package com.raytracer.core;

import com.raytracer.core.imaging.Frame;
import com.raytracer.core.scene.RayTracer;
import com.raytracer.core.scene.Scene;
import com.raytracer.core.scene.SceneFileParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scene scene = new SceneFileParser("D:\\DATEIN AB 10-2020\\! ! COURS\\IMT\\CI1\\raytracer\\core\\src\\main\\resources\\scenes\\jalon3\\tp31.test").parse();

        RayTracer rayTracer = new RayTracer(scene);
        Frame frame = new Frame(rayTracer);

        BufferedImage image = frame.render();
        Path path = Path.of(scene.getOutput());

        try {
            if (path.getParent() != null && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            boolean success = ImageIO.write(image, "png", path.toFile());

            if (!success) {
                System.err.println("Error writing render.png");
            }
        } catch (IOException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }
}
