package kernel;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GalaxyHunterView;


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

	private Kernel(){  }
	
	/**
	 * Returns an image with the filename specified.
	 * 
	 * @param imgpath name of file on hard drive
	 * @return an Image object that contains the image specified
	 */
	public BufferedImage loadImage(String imgpath) {
		// Get the image and load it into memory. Resource path should be added
		// to string here before finding the image
		imgpath = "images/" + imgpath;
		BufferedImage i = null;
		try {
			i = ImageIO.read(this.getClass().getClassLoader().getResource(imgpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}
	
	public static Kernel getInstance(){
		return KERNEL_INSTANCE;
	}

}