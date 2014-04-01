package entities;

import kernel.Kernel;

public class Player extends Ship {

	public Player(int x, int y) {
		super(Kernel.getInstance().loadImage("space_ship.jpg"), x, y);
		// TODO Auto-generated constructor stub
	}

}
