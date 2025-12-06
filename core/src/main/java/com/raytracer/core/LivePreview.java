package com.raytracer.core;

import com.raytracer.core.imaging.Frame;
import com.raytracer.core.scene.RayTracer;
import com.raytracer.core.scene.Scene;
import com.raytracer.core.scene.SceneFileParser;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class LivePreview {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter scene file absolute path for live preview:");
        String scenePath = scanner.nextLine();

        JFrame frame = new JFrame("RayTracer Live Preview");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Editor Area
        JTextArea textArea = new JTextArea(20, 40);
        JScrollPane textScrollPane = new JScrollPane(textArea);

        // Image Area
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JScrollPane imageScrollPane = new JScrollPane(imageLabel);

        // Split Pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, textScrollPane, imageScrollPane);
        splitPane.setResizeWeight(0.3);
        frame.add(splitPane);

        // Load file content
        try {
            String content = Files.readString(Path.of(scenePath));
            textArea.setText(content);
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            textArea.setText("# Error loading file: " + scenePath + "\n# " + e.getMessage());
        }

        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Render Thread
        new Thread(() -> {
            String lastRenderedContent = "";
            while (frame.isVisible()) {
                try {
                    String currentContent = textArea.getText();

                    // Only render if content changed
                    if (!currentContent.equals(lastRenderedContent)) {
                        lastRenderedContent = currentContent;

                        long start = System.currentTimeMillis();
                        
                        InputStream stream = new ByteArrayInputStream(currentContent.getBytes(StandardCharsets.UTF_8));
                        Scene scene = new SceneFileParser(stream).parse();

                        RayTracer rayTracer = new RayTracer(scene);
                        Frame renderer = new Frame(rayTracer);
                        BufferedImage image = renderer.render();

                        SwingUtilities.invokeLater(() -> {
                            imageLabel.setIcon(new ImageIcon(image));
                            imageLabel.repaint();
                        });

                        long duration = System.currentTimeMillis() - start;
                        System.out.println("Rendered in " + duration + "ms at " + java.time.LocalTime.now());
                    }
                } catch (Exception e) {
                    System.err.println("Error rendering scene: " + e.getMessage());
                }

                try {
                    Thread.sleep(1000); // 1 second delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}
