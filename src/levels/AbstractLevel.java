package levels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import kernel.Kernel;
import main.GameFrame;
import entities.Ship;
import factory.Cruiser;
import factory.ProjectileType;
import factory.ShipFactory;
import factory.ShipType;

@SuppressWarnings("serial")
public abstract class AbstractLevel extends JPanel implements KeyListener, Runnable {

	protected final int MOVEMENT_SPEED = 10;
	protected final int WIDTH = 400;
	protected final int HEIGHT = 600;
	protected final int STARTING_X = WIDTH / 2;
	protected final int STARTING_Y = (int) (HEIGHT - (double) HEIGHT / 6);
	protected final int NUM_KEYS = 256;
	protected final int THREAD_REGULATOR = 16;
	protected final int SIDE_SCREEN_BUFFER = 0;
	protected final Ship s = ShipFactory.spawnShip(ShipType.PLAYER, STARTING_X, STARTING_Y);
	protected final GameFrame frame = Kernel.getBaseJFrame();
	
	protected ArrayList<Cruiser> enemies = new ArrayList<Cruiser>(10);
	protected final boolean[] isPressed = new boolean[NUM_KEYS];
	protected final boolean[] isReleased = new boolean[NUM_KEYS];
	final Graphics g = getGraphics();
	
	public AbstractLevel() {
		
		new Thread(() -> {
			for (int i = 0; i < isPressed.length; i++) {
				isPressed[i] = false;
				isReleased[i] = false;
			}
		}).start();
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(frame.getWidth(), frame.getHeight());

		this.addKeyListener(this);
		frame.addKeyListener(this);

		frame.add(this);
		frame.setUndecorated(true);
		frame.setResizable(false);
		this.setDoubleBuffered(true);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void keyPressed(final KeyEvent e) {
		isPressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		isReleased[e.getKeyCode()] = true;
	}

	@Override
	public void keyTyped(final KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);

		frame.draw(g);
		s.draw(g);
		
		for (int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}

		this.revalidate();
	}
	
	protected void moveGameObjects() {
		for (int i = 0; i < enemies.size(); i++){
			enemies.get(i).move();
		}
	}
	
	protected void respondToInput() {
		if (isPressed[KeyEvent.VK_UP]) {
			if (s.getY() - SIDE_SCREEN_BUFFER > SIDE_SCREEN_BUFFER) {
				s.moveBy(0, -MOVEMENT_SPEED);
			}
			isPressed[KeyEvent.VK_UP] = false;
		}

		if (isPressed[KeyEvent.VK_DOWN]) {
			if (s.getY() + s.getHeight() < HEIGHT) {
				s.moveBy(0, MOVEMENT_SPEED);
			}
			isPressed[KeyEvent.VK_DOWN] = false;
		}

		if (isPressed[KeyEvent.VK_LEFT]) {
			if (s.getX() >= SIDE_SCREEN_BUFFER) {
				s.moveBy(-MOVEMENT_SPEED, 0);
			}
			
			isPressed[KeyEvent.VK_LEFT] = false;
		}

		if (isPressed[KeyEvent.VK_RIGHT]) {
			if (s.getX() + s.getWidth() <= WIDTH - SIDE_SCREEN_BUFFER) {
				s.moveBy(MOVEMENT_SPEED, 0);
			}
			
			isPressed[KeyEvent.VK_RIGHT] = false;
		}
		
		// Projectile should only be shot on release
		// User gets one projectile per press
		if (isReleased[KeyEvent.VK_SPACE]) {
			s.fire( ProjectileType.MISSILE );
			isReleased[KeyEvent.VK_SPACE] = false; 
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			respondToInput();
			moveGameObjects();

			this.repaint();

			try {
				Thread.sleep(THREAD_REGULATOR);
			} catch (final Exception x) {
			}

		}
	}
	
	/**
	 * All subclasses must define how many enemies are created
	 */
	public abstract void generateEnemies();
	
}
