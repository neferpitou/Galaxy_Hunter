package kernel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import entities.Entity;
import levels.LevelOne;
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

	private Kernel(){  }
	
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

	public static void startGame() {
		new LevelOne();
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

}
