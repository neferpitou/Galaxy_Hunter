package levels;

import entities.Cruiser;
import factory.PlayerShipType;

@SuppressWarnings("serial")
public class LevelOne extends AbstractLevel  {

	public LevelOne(PlayerShipType selected_ship) {
		super(selected_ship);

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

		// TODO Make enemies here
		for (int i = 0; i < 10; i++) {

			enemies.add(new Cruiser(200, starting_y));
			starting_y -= 300;
		}
	}
}
