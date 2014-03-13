package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import kernel.Kernel;
import entities.Ship;

@SuppressWarnings("serial")
public class GalaxyHunterView extends JPanel implements KeyListener, Runnable {
	
	private class GameFrame extends JFrame {
		
		private Image galaxy_image;
		private int g_img_y, c_img_y;
		private int img_height;
		
		public GameFrame( ){
			// Ask the kernel to load the image
			galaxy_image = Kernel.getInstance().loadImage("stars_texture2956.jpg");
			
			// Set the location for the first image at the bottom of the screen
			g_img_y = HEIGHT;						
			img_height = galaxy_image.getHeight(null);
			
			// Set the location of the second image above the first image
			c_img_y = HEIGHT - img_height;
		}
		
		public void draw (Graphics g){
			
			// Scroll both backgrounds
			g_img_y++;
			c_img_y++;
			
			// Draw the background twice to the panel
			g.drawImage(galaxy_image, 0, g_img_y, null);
			g.drawImage(galaxy_image, 0, c_img_y, null);
			
			/*
			 * Calculate if one of the two background images are completely
			 * off the screen. If so, redraw the image on top of the currently
			 * visible image
			 */
			if (g_img_y > HEIGHT + img_height) 
				g_img_y = c_img_y - img_height;
			
			
			if (c_img_y > HEIGHT + img_height)
				c_img_y = g_img_y - img_height;				
		}
	}
	
	private GameFrame frame = new GameFrame();
	
	private final int MOVEMENT_SPEED = 5;
	private final int WIDTH = 400;
	private final int HEIGHT = 600;
	private final int STARTING_X = WIDTH / 2;
	private final int STARTING_Y = (int) (HEIGHT - ((double) HEIGHT) / 6 );
	private final int NUM_KEYS = 256;
	private final int THREAD_REGULATOR = 16;
	private final int SIDE_SCREEN_BUFFER = 0;
	private Ship s = new Ship(STARTING_X, STARTING_Y);
	
	private boolean[] isPressed = new boolean[NUM_KEYS];

	public GalaxyHunterView() {
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < isPressed.length; i++) {
					isPressed[i] = false;
				}	
			}
		}).start();
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(frame.getWidth(), frame.getHeight());
	
		addKeyListener(this);
		frame.addKeyListener(this);

		frame.add(this);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		

		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		isPressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isPressed[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			
			if (isPressed[KeyEvent.VK_UP]) {
				if ( s.getY() - SIDE_SCREEN_BUFFER > SIDE_SCREEN_BUFFER ) 
					s.moveBy(0, -MOVEMENT_SPEED);
			}

			if (isPressed[KeyEvent.VK_DOWN]) {
				if ( s.getY() + s.getHeight() < HEIGHT )  
					s.moveBy(0, MOVEMENT_SPEED);
			}

			if (isPressed[KeyEvent.VK_LEFT]) {
				if ( s.getX() >= SIDE_SCREEN_BUFFER ) 
					s.moveBy(-MOVEMENT_SPEED, 0);
			}

			if (isPressed[KeyEvent.VK_RIGHT]) {
				if ( s.getX() + s.getWidth() <= WIDTH - SIDE_SCREEN_BUFFER ) 
					s.moveBy(MOVEMENT_SPEED, 0);
			}

			repaint();

			try {
				Thread.sleep(THREAD_REGULATOR);
			} catch (Exception x) {
			}
			
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		frame.draw(g);
		s.draw(g);
		
		revalidate();
	}
}
