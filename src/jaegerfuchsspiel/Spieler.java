/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaegerfuchsspiel;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Name
 */
public abstract class Spieler {
    
    public Spieler(int maxWidth, int maxHeight){
        width = maxWidth;
        height = maxHeight;
        wuerfel = new Random();
    }
    
    private static final int RADIUS = 20;
    protected Random wuerfel;
    protected int width;
    protected int height;
    
    public abstract void aenderePosition();
    public abstract int gibX();
    public abstract int gibY();        
    public abstract BufferedImage gibBild();
    
    public Point erzeugeNeuePosition(){
        Point p = new Point();
        p.x = wuerfel.nextInt(RADIUS + 1);
        p.y = (int) Math.sqrt(RADIUS * RADIUS - p.x * p.x);
        return p;
    }
}

