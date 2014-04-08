package factory;

import entities.Cruiser;
import entities.Player;
import entities.Ship;

public class ShipFactory {

	public static Ship spawnShip(ShipType a, int x, int y) {
		Ship entity = null;

		switch (a) {
		case CRUISER:
			entity = new Cruiser(x, y);
			break;

		default:
			// throw some exception
			break;
		}

		return entity;
	}

	public static Ship spawnShip(PlayerShipType a, int x, int y) {
		String shipIcon = "";

		switch (a) {
		case TALON:
			shipIcon = "talon.png";
			break;
		case VIXEN:
			shipIcon = "vixen.png";
			break;
		case KNIGHT:
			shipIcon = "knight.png";
			break;
		default:
			shipIcon = "talon.png";
			break;
		}

		return new Player(shipIcon, x, y);
	}

}
