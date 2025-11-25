package com.raytracer.imgcompare;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java com.raytracer.imgcompare.Main <image1> <image2>");
            System.exit(1);
        }

        try {
            BufferedImage image1 = ImageIO.read(new File(args[0]));
            BufferedImage image2 = ImageIO.read(new File(args[1]));

            ImageComparator comparator = new ImageComparator(image1, image2);
            System.out.println(comparator.compare());

            BufferedImage difference = comparator.difference();
            Path path = Path.of("difference.png");

            if (path.getParent() != null && !Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            boolean success = ImageIO.write(difference, "png", path.toFile());

            if (!success) {
                System.err.println("Error writing difference");
            }

        } catch (IOException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }
}
