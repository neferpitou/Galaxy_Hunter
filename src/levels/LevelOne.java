package levels;

import kernel.Kernel;
import entities.Cruiser;
import factory.PlayerShipType;

@SuppressWarnings("serial")
public class LevelOne extends AbstractLevel  {
	
	private final int NUM_ENEMIES = 30;
	private Kernel kernel = Kernel.getInstance();

	public LevelOne(PlayerShipType selected_ship) {
		super(selected_ship);
		kernel.playBGM("08 Return to Planet X.mp3");
		
		generateEnemies();
		
		final Thread t = new Thread(this);
		t.start();
	}
	
	/**
	 * Generates the starting positions for all enemy cruisers
	 * for this level.
	 */
	@Override
	public void generateEnemies(){
		int starting_y = -100;
		// To make sure all the images are within the screen
		// this stops ships from spawning along the right edge
		// half on and half off the screen
		final int OFFSET_RIGHT = -20;

		// TODO Make enemies here
		for (int i = 0; i < NUM_ENEMIES; i++) {
			int random_x_pos = (int) (Math.random() * Kernel.WIDTH + OFFSET_RIGHT);
			int random_y_pos = (int) -(Math.random() * Kernel.HEIGHT) + starting_y;
			enemies.add(new Cruiser(random_x_pos, random_y_pos));
			starting_y -= 300;
		}
	}
}
