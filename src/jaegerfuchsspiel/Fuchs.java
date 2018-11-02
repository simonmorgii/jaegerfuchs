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
public class Fuchs extends Spieler {
    private BufferedImage fuchsImg;
    private Point[] naechstePositionen;
    private Spieler derJaeger;

    public Fuchs(int maxWidth, int maxHeight) {
        super(maxWidth, maxHeight);
        naechstePositionen = new Point[5];
        try {
            File imgSource = new File("img/fox.png");
            fuchsImg = ImageIO.read(imgSource);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void aenderePosition() {
        for (int i = 0; i < naechstePositionen.length - 1; i++) {
            naechstePositionen[i] = naechstePositionen[i + 1];
        }
        Point change = erzeugeNeuePosition();
        Point p = new Point();
        // Berechnung x Veranderung
        if (naechstePositionen[3].x <= derJaeger.gibX() && naechstePositionen[3].x - change.x > 0) {
            p.x = naechstePositionen[3].x - change.x;
        } else if (naechstePositionen[3].x <= derJaeger.gibX() && naechstePositionen[3].x - change.x <= 0) {
            p.x = naechstePositionen[3].x + change.x;
        } else if (naechstePositionen[3].x > derJaeger.gibX() && naechstePositionen[3].x + change.x <= width - 100) {
            p.x = naechstePositionen[3].x + change.x;
        } else if (naechstePositionen[3].x > derJaeger.gibX() && naechstePositionen[3].x + change.x > width - 100) {
            p.x = naechstePositionen[3].x - change.x;
        }
        // Berechnung y Veraenderung
        if (naechstePositionen[3].y <= derJaeger.gibY() && naechstePositionen[3].y - change.y > 0) {
            p.y = naechstePositionen[3].y - change.y;
        } else if (naechstePositionen[3].y <= derJaeger.gibY() && naechstePositionen[3].y - change.y <= 0) {
            p.y = naechstePositionen[3].y + change.y;
        } else if (naechstePositionen[3].y > derJaeger.gibY() && naechstePositionen[3].y + change.y <= height - 100) {
            p.y = naechstePositionen[3].y + change.y;
        } else if (naechstePositionen[3].y > derJaeger.gibY() && naechstePositionen[3].y + change.y > height - 100) {
            p.y = naechstePositionen[3].y - change.y;
        }
        naechstePositionen[naechstePositionen.length - 1] = p;
    }

    public void setJaeger(Jaeger jaeger) {
        this.derJaeger = jaeger;
    }

    @Override
    public int gibX() {
        return naechstePositionen[0].x;
    }

    @Override
    public int gibY() {
        return naechstePositionen[0].y;
    }

    public void erzeugeStartPositionen() {
        for (int i = 0; i < naechstePositionen.length; i++) {
            naechstePositionen[i] = new Point();
        }
        naechstePositionen[0].x = wuerfel.nextInt(width);
        naechstePositionen[0].y = wuerfel.nextInt(height);
        for (int i = 1; i < naechstePositionen.length; i++) {
            Point change = erzeugeNeuePosition();
            if (derJaeger.gibX() >= naechstePositionen[i - 1].x && naechstePositionen[i - 1].x - change.x > 0) {
                naechstePositionen[i].x = naechstePositionen[i - 1].x - change.x;
            } else if (derJaeger.gibX() >= naechstePositionen[i - 1].x && naechstePositionen[i - 1].x - change.x < 0) {
                naechstePositionen[i].x = naechstePositionen[i - 1].x + change.x;
            } else if (derJaeger.gibX() < naechstePositionen[i - 1].x && naechstePositionen[i - 1].x + change.x < width) {
                naechstePositionen[i].x = naechstePositionen[i - 1].x + change.x;
            } else if (derJaeger.gibX() < naechstePositionen[i - 1].x && naechstePositionen[i - 1].x + change.x > width) {
                naechstePositionen[i].x = naechstePositionen[i - 1].x - change.x;
            }
            if (derJaeger.gibY() >= naechstePositionen[i - 1].y && naechstePositionen[i - 1].y - change.y > 0) {
                naechstePositionen[i].y = naechstePositionen[i - 1].y - change.y;
            } else if (derJaeger.gibY() >= naechstePositionen[i - 1].y && naechstePositionen[i - 1].y - change.y < 0) {
                naechstePositionen[i].y = naechstePositionen[i - 1].y + change.y;
            } else if (derJaeger.gibY() < naechstePositionen[i - 1].y && naechstePositionen[i - 1].y + change.y < height) {
                naechstePositionen[i].y = naechstePositionen[i - 1].y + change.y;
            } else if (derJaeger.gibY() < naechstePositionen[i - 1].y && naechstePositionen[i - 1].y + change.y > height) {
                naechstePositionen[i].y = naechstePositionen[i - 1].y - change.y;
            }
        }
        for (Point naechstePositionen1 : naechstePositionen) {
            System.out.println(naechstePositionen1);
        }
    }

    @Override
    public BufferedImage gibBild() {
        return fuchsImg;
    }

}
