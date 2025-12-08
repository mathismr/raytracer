package com.raytracer.core;

import com.raytracer.core.imaging.Frame;
import com.raytracer.core.scene.RayTracer;
import com.raytracer.core.scene.Scene;
import com.raytracer.core.scene.SceneFileParser;
import com.raytracer.imgcompare.ImageComparator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter scene file absolute path:");
        String scenePath = scanner.nextLine();

        SceneFileParser parser = new SceneFileParser();
        Scene scene = parser.parse(scenePath);
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

        System.out.println("Enter the absolute path of an image to compare (optional, press Enter to skip):");
        String imagePath = scanner.nextLine();

        if (!imagePath.isEmpty()) {
            try {
                BufferedImage compareTo = ImageIO.read(new File(imagePath));
                ImageComparator comparator = new ImageComparator(image, compareTo);
                BufferedImage difference = comparator.difference();
                ImageIO.write(difference, "png", new File("difference.png"));
            } catch (IOException e) {
                System.err.println("Error : " + e.getMessage());
            }
        }
    }
}
