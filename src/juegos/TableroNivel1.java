/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegos;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author USER
 */
public class TableroNivel1 extends Tablero{
    	
	public TableroNivel1() {
		// create some default map data - it would be way
		// cooler to load this from a file and maybe provide
		// a map editor of some sort, but since we're just doing
		// a simple tutorial here we'll manually fill the data
		// with a simple little map
		for (int y=0;y<HEIGHT;y++) {
                    for(int x=0;x<WIDTH;x++) {
                    System.out.println(WIDTH);
                    tableroMatriz[x][y]=BLOCKED;
                    
                   
                    }
                    if(y>0 && y<20)
                        tableroMatriz[1][y]=CLEAR;
                    
                    if(y>7 && y<=18)
                        tableroMatriz[8][y]=CLEAR;
                    
                    if(y>11 && y<=18)
                        tableroMatriz[12][y]=CLEAR;
                    if(y>15 && y<=21)
                        tableroMatriz[10][y]=CLEAR;
                    if(y>20 && y<=24)
                        tableroMatriz[14][y]=CLEAR;
                    if(y>18 && y<=29)
                        tableroMatriz[25][y]=CLEAR;
                
                
                
                }
                        
		for (int x=0;x<WIDTH;x++) {
			if ((x > 0) && (x < WIDTH-1)) {
                            tableroMatriz[x][10]=CLEAR;
                            //tableroMatriz[x][] = CLEAR;
			}
                        if (x > 10 && (x < WIDTH-1)) {
                             tableroMatriz[x][20] = CLEAR;
                        }
                         if(x>=8 && x<=13){
                            tableroMatriz[x][12] = CLEAR;
                        }
                        if(x>=8 && x<=11){
                            tableroMatriz[x][17] = CLEAR;
                            tableroMatriz[x][15] = CLEAR;
                        }
                        if(x>=10 && x<=15){
                            tableroMatriz[x][25] = CLEAR;
                        }
                        if(x>=20 && x<=25){
                            tableroMatriz[x][25] = CLEAR;
                        }
                        if(x>=26 && x<=28){
                            tableroMatriz[x][29] = CLEAR;
                        }
                        
                       
                        
			//tableroMatriz[x][0] = BLOCKED;
			//tableroMatriz[x][HEIGHT-1] = BLOCKED;
		}
		
		/*tableroMatriz[4][9] = CLEAR;
		tableroMatriz[7][5] = CLEAR;
		tableroMatriz[7][4] = CLEAR;
		tableroMatriz[11][7] = CLEAR;*/
	}
	
	/**
	 * Render the map to the graphics context provided. The rendering
	 * is just simple fill rectangles
	 * 
	 * @param g The graphics context on which to draw the map
	 */
	
	
	/**
	 * Check if a particular location on the map is blocked. Note
	 * that the x and y parameters are floating point numbers meaning
	 * that we can be checking partially across a grid cell.
	 * 
	 * @param x The x position to check for blocking
	 * @param y The y position to check for blocking
	 * @return True if the location is blocked
	 */
	public boolean blocked(float x, float y) {
		// look up the right cell (based on simply rounding the floating
		// values) and check the value
		return tableroMatriz[(int) x][(int) y] == BLOCKED;
	}
}
