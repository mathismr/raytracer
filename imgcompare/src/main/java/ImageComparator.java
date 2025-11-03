import java.awt.image.BufferedImage;

public class ImageComparator {
    private final BufferedImage image1;
    private final BufferedImage image2;

    public ImageComparator(BufferedImage image1, BufferedImage image2) {
        this.image1 = image1;
        this.image2 = image2;
    }

    public int compare() {
        int w1 = image1.getWidth();
        int h1 = image1.getHeight();
        int w2 = image2.getWidth();
        int h2 = image2.getHeight();

        if (w1 != w2 && h1 != h2) {
            System.err.println("Error: images are not same width and height");
            return -1;
        }

        int counter = 0;

        for  (int y = 0; y < h1; y++) {
            for (int x = 0; x < w1; x++) {
                int r1 = image1.getRGB(x, y);
                int r2 = image2.getRGB(x, y);

                if (r1 != r2) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public BufferedImage difference() {
        int w1 = image1.getWidth();
        int h1 = image1.getHeight();
        int w2 = image2.getWidth();
        int h2 = image2.getHeight();

        if (w1 != w2 && h1 != h2) {
            System.err.println("Error: images are not same width and height");
            return image1;
        }

        BufferedImage difference = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h1; y++) {
            for (int x = 0; x < w1; x++) {
                int r1 = image1.getRGB(x, y);
                int r2 = image2.getRGB(x, y);

                if (r1 == r2) {
                    difference.setRGB(x, y,0);
                } else {
                    difference.setRGB(x, y, r1-r2);
                }
            }
        }
        return difference;
    }
}