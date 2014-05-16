package entities;

import factory.ProjectileType;
import kernel.Kernel;

public class Knight extends Ship {

	final static ProjectileType PROJ = ProjectileType.LASERS;

	public Knight(String shipIcon, int x, int y, int SPEED) {	
		super(Kernel.getInstance().loadImage(shipIcon), SPEED, x, y, PROJ);
		health = 150;
	}

}