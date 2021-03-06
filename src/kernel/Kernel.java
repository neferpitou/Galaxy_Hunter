package kernel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import entities.Entity;
import factory.PlayerShipType;
import levels.LevelOne;
import main.GalaxyHunterMenu;
import main.GameFrame;


/**
 * The kernel of the video game, where the bulk of the logic will be. It houses
 * functionality common to many classes in the project and also handles
 * additional logic that should not be in the domain of those classes.
 * 
 * The kernel is also responsible for managing many of the most important
 * threads in the game. It also provides the game with services for loading
 * images, drawing to the screen, asynchronous input, collision detection, and
 * background music.
 * 
 * In order for classes to be able to utilize the methods within the kernel, an
 * instance of the kernel is be passed as a parameter to any constructor of that
 * class. This parameter is always final to avoid accidental changes. Any
 * updates that need to happen to the kernel should be handled by a function
 * call to the kernel from within that class.
 * 
 * @author Marcos Davila
 */

public class Kernel {
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
	//Private instance reference of the kernel
	private static final Kernel KERNEL_INSTANCE = new Kernel();
	private static final GameFrame frame = new GameFrame();
	private ThreadPool thread_pool;
	private final int MAX_NUM_THREADS = 5;
	private BGMRunnable bgm_thread;
	
	private Kernel(){
		thread_pool = new ThreadPool(MAX_NUM_THREADS);
		
		// Instantiate two instances of each character (in case both players
		// want to use the same character) in a worker thread
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			thread_pool.close();
		}));
		
		thread_pool.runTask(() -> {
			startMenu();
		});
	}
	
	/**
	 * Returns an image with the filename specified.
	 * 
	 * @param imgpath name of file on hard drive
	 * @return an Image object that contains the image specified
	 */
	public Image loadImage(String imgpath) {
		// Get the image and load it into memory. Resource path should be added
		// to string here before finding the image
		imgpath = "images/" + imgpath;
		return new ImageIcon(this.getClass().getClassLoader().getResource(imgpath)).getImage();
	}
	
	public static Kernel getInstance(){
		return KERNEL_INSTANCE;
	}
	
	/**
	 * Returns the frame panels are drawn on
	 * @return jframe
	 */
	public static GameFrame getBaseJFrame(){
		return frame;
	}

	public static void startMenu() {
		new GalaxyHunterMenu();
	}
	
	public static void startGame(PlayerShipType selected_ship) {
		new LevelOne(selected_ship);
	}
	
	/**
	 * Streams the desired resource from the resources folder
	 * @param location location to stream from
	 * @return an Input stream of the resource
	 */
	public BufferedInputStream loadResourceAsStream(String location){
		return new BufferedInputStream(ClassLoader.getSystemResourceAsStream("resources/" + location));
	}
	
	public static boolean collisionDetected(Entity e1, Entity e2) {

		/*
		 * Two rectangles do not overlap when one is above/below, or to the
		 * left/right of the other rectangle.
		 */
		
		int e1x1 = e1.getX();
		int e1x2 = e1.getX()+e1.getWidth();
		int e1y1 = e1.getY();
		int e1y2 = e1.getY()+e1.getHeight();
		int e2x1 = e2.getX();
		int e2x2 = e2.getX()+e2.getWidth();
		int e2y1 = e2.getY();
		int e2y2 = e2.getY()+e2.getHeight();
		
		return ((e1x2 >= e2x1) &&
			    (e1y2 >= e2y1) &&
			    (e1x1 <= e2x2) &&
			    (e1y1 <= e2y2));
	}
	
	/**
	 * Plays the specified file as looping background music in it's own thread.
	 * @param filename name of the file to be played sitting in resources folder.
	 */
	public void playBGM(String filename) {
		bgm_thread = new BGMRunnable(filename);
		thread_pool.runTask(bgm_thread);
	}
		
	
	/**
	 * Stops the background music from playing
	 */
	/*
	 * Only stops the background music if there is a non-null Runnable instance in the kernel.
	 * Every class that plays background music should stop the BGM before moving to the next 
	 * screen.
	 */
	public boolean stopBGM() {
		bgm_thread.halt();
		thread_pool.removeTask(bgm_thread);
		return true;	// signals that the bgm thread has been halted
	}


}
