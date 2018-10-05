package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Renderer {

    private BufferedImage img;
    private Canvas canvas;
    private static final int FPS = 1000/30;

    public Renderer(BufferedImage img, Canvas canvas) {
        this.img = img;
        this.canvas = canvas;
        setLoop();
    }

    private void setLoop() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // říct plátnu, aby zobrazil aktuální img
                canvas.getGraphics().drawImage(img, 0, 0, null);
                // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
            }
        },0,FPS);
    }

    public void clear(){
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.clearRect(0,0,800,600);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color){
        int dy = y2-y1;
        int dx = x2-x1;
        float k = (float)dy/dx;
        float q = y1-(k*x1);

        if(Math.abs(k) < 1){ //ridici osa x

            if (x1 > x2) { //kada je x1>x2 onda moramo da zamenimo vrednosti (kao ogledalo)
                int pomocna = x1;
                x1 = x2;
                x2 = pomocna;
            }

            for (int x = x1; x <= x2; x++) { //Triviální algoritmus
                int y = Math.round(k * x + q);
                drawPixel(x, y, color);
            }
        } else { //ridici osa y

            if(y1>y2){
                int pomocna = y1;
                y1=y2;
                y2=pomocna;
            }

            for (int y = y1; y<=y2; y++) {
                int x = Math.round((y-q)/k);
                drawPixel(x,y,color);
            }
        }

    }

    public void drawPixel(int x, int y, int color) {
        // nastavit pixel do img
        img.setRGB(x, y, color);
    }
}