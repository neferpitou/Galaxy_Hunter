package animation;

import java.awt.*;

/**
 * This class loads images and also fetches the next image.
 * 
 * @author Marcos
 *
 */
public class Animation {

	private Image[] image;
	private int current = 0;
	private int delay = 0;
	private int DELAY_IN_SEC = 10;
	
	public Animation(String name, int count){
		image = new Image[count];
		
		for (int i = 0; i < count; i++){
			image[i] = Toolkit.getDefaultToolkit().getImage(name + "_" + i + ".gif");
		}
	}
	
	public Image nextImage(){
		if (delay == DELAY_IN_SEC){
			delay = -1;
			
			current = (current + 1) & image.length;
		}
		
		delay++;
		
		return image[current];
	}
}
