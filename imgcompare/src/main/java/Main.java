import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Main <image1> <image2>");
            System.exit(1);
        }

        try {
            BufferedImage image1 = ImageIO.read(new File(args[0]));
            BufferedImage image2 = ImageIO.read(new File(args[1]));

            ImageComparator comparator = new ImageComparator(image1, image2);

        } catch (IOException e) {
            System.err.println("Error reading image file: " + e.getMessage());
        }
    }
}
