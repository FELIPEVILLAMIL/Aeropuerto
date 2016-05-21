/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import juegos.*;
        

/**
 *
 * @author USER
 */
public class Juegos extends Canvas implements KeyListener,ActionListener{
    private Tablero tablero;
    private Timer timer;
    private BufferStrategy strategy;
    private Entity player;
    private Image sprite;
    private ArrayList<Esfera> esfera;
    	private boolean left;
	/** True if the right key is currently pressed */
	private boolean right;
	/** True if the up key is currently pressed */
	private boolean up;
	/** True if the down key is currently pressed */
	private boolean down;

    public Juegos() throws FileNotFoundException{
        sprite = loadImage("sprite.gif");
        JFrame frame=new JFrame();
        frame.setSize(800,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1000,1000);
        frame.add(this);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(this);
		addKeyListener(this);
        tablero=new TableroNivel1();
        player= new Entity(sprite, tablero, 1.5f, 1.5f);
        this.esfera=new ArrayList<Esfera>();
        //this.esfera.add(new Esfera(300,500));
        this.esfera.add(new Esfera(20,50));
        this.timer = new Timer(100,this);
         this.timer.start();
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        gameLoop();
    }
    public void gameLoop() throws FileNotFoundException {
		boolean gameRunning = true;
		long last = System.nanoTime();
                int level = 1;
		
		// keep looking while the game is running
		while (gameRunning) {
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			
			// clear the screen
			g.setColor(Color.lightGray);
			g.fillRect(0,0,1000,800);
			
			// render our game objects
			g.translate(100,20);
                        
			tablero.paint(g, (int)(player.x), (int)(player.y));
                        player.paint(g);
                        
                        if (esfera!=null){
                             for(Esfera esfera:esfera)
                             esfera.dibujar(g,this);
                            
                        }
                       
                        

			// flip the buffer so we can see the rendering
			g.dispose();
			strategy.show();
			
			// pause a bit so that we don't choke the system
			try { Thread.sleep(4); } catch (Exception e) {};
			
			// calculate how long its been since we last ran the
			// game logic
			long delta = (System.nanoTime() - last) / 1000000;
			last = System.nanoTime();
		
			// now this needs a bit of explaining. The amount of time
			for (int i=0;i<delta / 5;i++) {
				logic(5);
			}
			// after we've run through the segments if there is anything
			// left over we update for that
			if ((delta % 5) != 0) {
				logic(delta % 5);
			}
                        
                        if(validarColisiones()) {
                            if(level == 1) {
                                level++;
                                tablero = new TableroNivel2();
                                 this.esfera=new ArrayList<Esfera>();
                                  
                                  this.esfera.add(new Esfera(300,500));
                                  this.esfera.add(new Esfera(20,50));
                            }
                            else if(level==2){
                            level++;
                            tablero=new TableroNivel3();
                             this.esfera=new ArrayList<Esfera>();
                             this.esfera.add(new Esfera(300,200));
                        }
                            
                            player = new Entity(sprite, tablero, 1.5f, 1.5f);
                        }
			
		}
	}
    public void logic(long delta) {
		// check the keyboard and record which way the player
		// is trying to move this loop
                 validarColisiones();
		float dx = 0;
		float dy = 0;
		if (left) {
			dx -= 1;
		}
		if (right) {
			dx += 1;
		}
		if (up) {
			dy -= 1;
		}
		if (down) {
			dy += 1;
		}
		
		// if the player needs to move attempt to move the entity
		// based on the keys multiplied by the amount of time thats
		// passed
		if ((dx != 0) || (dy != 0)) {
			player.move(dx * delta * 0.003f,
						dy * delta * 0.003f);
		}
	}
	
	/**
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		// check the keyboard and record which keys are pressed
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
	}

	/**
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {
		// check the keyboard and record which keys are released
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
	}
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Juegos();
    }

    protected Image loadImage(String imageName) {
             ImageIcon ii = new ImageIcon(imageName);
             Image image = ii.getImage();
             return image;
         } 
   
    public boolean validarColisiones(){
                Rectangle recPersonaje=this.player.obRectangle();
                for (Esfera esfera: esfera){
                Rectangle RecEsfera=esfera.obRectangle();
                ArrayList<Esfera> copia=(ArrayList<Esfera>)this.esfera.clone();
                    if(recPersonaje.intersects(RecEsfera)){
                        System.out.println("Colision");
                        copia.remove(esfera);
                        return true;
                    }
                    this.esfera=copia;
                }
                return false;
}

    @Override
    public void actionPerformed(ActionEvent e) {
        validarColisiones();
         for(Esfera esfera: this.esfera)
            esfera.mover();
            repaint();
        
    }
}
    

