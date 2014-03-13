package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	private JFrame frame = new JFrame();
	
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
		s.draw(g);
		revalidate();
	}
}
