package entities;

import factory.ProjectileType;
import kernel.Kernel;

public class Talon extends Ship {


	final static ProjectileType PROJ = ProjectileType.MISSILE;
	
	public Talon(String shipIcon, int x, int y, int SPEED) {	
		super(Kernel.getInstance().loadImage(shipIcon), SPEED, x, y, PROJ);
		health = 100;
	}

}
