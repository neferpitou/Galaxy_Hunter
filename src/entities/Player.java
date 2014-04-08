package entities;

import factory.PlayerShipType;
import kernel.Kernel;

public class Player extends Ship {

	public Player(String shipIcon, int x, int y) {	
		super(Kernel.getInstance().loadImage(shipIcon), x, y);
		// TODO Auto-generated constructor stub
	}

}
