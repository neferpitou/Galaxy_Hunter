package factory;

import entities.Ship;

public class ShipFactory {

	public static Ship spawnShip(ShipType a, int x, int y){
		Ship entity = null;

		switch (a) {
		case PLAYER:
			entity = new Player(x, y);
		case CRUISER:
			entity = new Cruiser(x, y);
			break;

		default:
			// throw some exception
			break;
		}
		
		return entity;
	}

}
