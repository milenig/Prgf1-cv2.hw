package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class pixeltestcv2 {

    private JFrame window;
    private BufferedImage img; // objekt pro zápis pixelů
    private Canvas canvas; // plátno pro vykreslení BufferedImage
    private Renderer renderer;

    public pixeltestcv2() {
        window = new JFrame();

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        window.setSize(800, 600); // velikost okna
        window.setLocationRelativeTo(null);// vycentrovat okno
        window.setTitle("PGRF1 cvičení"); // titulek okna

        // inicializace image, nastavení rozměrů (nastavení typu - pro nás nedůležité)
        img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

        // inicializace plátna, do kterého budeme kreslit img
        canvas = new Canvas();

        window.add(canvas); // vložit plátno do okna
        window.setVisible(true); // zobrazit okno

        renderer = new Renderer(img, canvas);

        renderer.drawPixel(100, 50, Color.GREEN.getRGB()); // 0x00ff00 == Color.GREEN.getRGB()
        renderer.drawLine(0,1,8,4,0xffff00); //yellow line

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                renderer.drawPixel(e.getX(), e.getY(), 0xffffff);
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                renderer.clear();
                renderer.drawLine(400,300, e.getX(), e.getY(), 0x00ffff);
            }
        });
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(pixeltestcv2::new); //pousteni aplikace
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
        // https://www.google.com/search?q=java+double+colon
    }
}