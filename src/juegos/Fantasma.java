/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Felipe
 */
public class Fantasma {
        int x;
	/** The y position of this entity in terms of grid cells */
	int y;
	/** The image to draw for this entity */
	private Image image;
	/** The map which this entity is wandering around */
	private Tablero map;
	/** The angle to draw this entity at */
	private int saltos;
	private int size=((300));
        
        
    public Fantasma(int x, int y,Image image, Tablero map,int saltos) {
        this.x = x;
        this.y = y;
        this.image=image;
        this.map = map;
        this.saltos= saltos;
    }
    public void move() {
       
        if ((x+saltos)>=size){
            saltos=-saltos;
        }
        if((x+saltos)<=0){
            saltos=-saltos;
        }
        x+=saltos;
            
    }
     protected Image loadImage(String imageName) {
       ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
         return image;
    }
    public void dibujar(Graphics g){
       g.setColor(Color.red);
       g.drawImage(image, x, y, null);
       
    }
     
     public Rectangle obRectangle(){
         return new Rectangle(this.x, this.y, 40, 40);
    }
        
    
}
