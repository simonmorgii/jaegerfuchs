/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaegerfuchsspiel;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Name
 */
public class Jaeger extends Spieler {

    private Point position;
    private Spieler derFuchs;
    private BufferedImage jaegerImg;
    public Jaeger(int maxWidth, int maxHeight) {
        super(maxWidth, maxHeight);
        position = new Point();
        position.x = wuerfel.nextInt(width);
        position.y = wuerfel.nextInt(height);
        try {
            File imgSource = new File("img/hunter.png");
            jaegerImg = ImageIO.read(imgSource);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void aenderePosition() {
        Point change = erzeugeNeuePosition();
        // Berechnung der X Veraenderung
        if (position.x <= derFuchs.gibX() && position.x + change.x < width - 100) {
            position.x += change.x;
        } else if (position.x <= derFuchs.gibX() && position.x + change.x > width - 100) {
            position.x -= change.x;
        } else if (position.x > derFuchs.gibX() && position.x - change.x > 0) {
            position.x -= change.x;
        } else if (position.x > derFuchs.gibX() && position.x - change.x < 0) {
            position.x += change.x;
        }
        // Berechnung der Y Veraenderung
        if (position.y <= derFuchs.gibY() && position.y + change.y < height - 100) {
            position.y += change.y;
        } else if (position.y <= derFuchs.gibY() && position.y + change.y > height - 100) {
            position.y -= change.y;
        } else if (position.y > derFuchs.gibY() && position.y - change.y > 0) {
            position.y -= change.y;
        } else if (position.y > derFuchs.gibY() && position.y - change.y < 0) {
            position.y += change.y;
        }
    }

    @Override
    public int gibX() {
        return position.x;
    }

    @Override
    public int gibY() {
        return position.y;
    }

    public void setFuchs(Fuchs fuchs) {
        this.derFuchs = fuchs;
    }

    public boolean isCatched() {
        if (position.x < derFuchs.gibX() && position.y < derFuchs.gibY()) {
            if (derFuchs.gibX() - position.x < 20 && derFuchs.gibY() - position.y < 20) {
                return true;
            }
        }
        if (position.x < derFuchs.gibX() && position.y > derFuchs.gibY()) {
            if (derFuchs.gibX() - position.x < 20 && position.y - derFuchs.gibY() < 20) {
                return true;
            }
        }
        if(position.x > derFuchs.gibX() && position.y < derFuchs.gibY()){
            if(position.x - derFuchs.gibX() < 20 && derFuchs.gibY() - position.y < 20){
                return true;
            }
        }
        if(position.x > derFuchs.gibX() && position.y > derFuchs.gibY()){
            if(position.x - derFuchs.gibX() < 20 && position.y - derFuchs.gibY() < 20){
                return true;
            }
        }
        return false;
    }

    @Override
    public BufferedImage gibBild() {
        return jaegerImg;
    }
}
