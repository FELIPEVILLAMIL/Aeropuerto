/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class Tablero  {
        protected static final int CLEAR = 0; /**Valor de las celdas cuando se encuentra vacia */
	protected static final int BLOCKED = 1;/** Valor de la celda cuando esta bloqueada */
	protected static final int WIDTH = 30;/** Ancho de matriz, numero de cuadros*/
	protected static final int HEIGHT = 30;/** Alto de matriz, numero de cuadros*/
	protected static final int TILE_SIZE =20;/**Tamaño de los cuadros*/
	protected int[][] tableroMatriz = new int[WIDTH][HEIGHT];
        

    public Tablero() {
        
    }
    protected void paint(Graphics2D g, int i, int j) {
		// loop through all the tiles in the map rendering them
		// based on whether they block or not
                  
		for (int x=0;x<WIDTH;x++) {
			for (int y=0;y<HEIGHT;y++) {
				g.setColor(Color.gray);//fondo de la matriz
				//if (x >= i - 10 && x <= i + 10 && y >= j - 10 && y <= j + 10) {
					
                                        
                                        
                                        //if(blocked(x, y)) {
                                           // g.setColor(Color.gray);
                                       
                        
                                        
				
				
				// draw the rectangle with a dark outline
				g.fillRect(x*TILE_SIZE,y*TILE_SIZE,TILE_SIZE,TILE_SIZE);
				g.setColor(g.getColor().darker());
				g.drawRect(x*TILE_SIZE,y*TILE_SIZE,TILE_SIZE,TILE_SIZE);
              }
        }
    }
		
    public boolean blocked(float x, float y) {
		// look up the right cell (based on simply rounding the floating
		// values) and check the value
		return tableroMatriz[(int) x][(int) y] == BLOCKED;
	}

    

        
       
}
