package entities;

import factory.GalacticFactory;
import factory.ProjectileType;
import kernel.Kernel;

public class Vixen extends Ship {

	final static ProjectileType PROJ = ProjectileType.ENERGY;
	
	public Vixen(String shipIcon, int x, int y, int SPEED) {	
		super(Kernel.getInstance().loadImage(shipIcon), SPEED, x, y, PROJ);
		health = 50;
	}
	
	@Override
	public void fire( ) {
		getProjs().add(GalacticFactory.spawnProjectile(projectileType, x, y));
		getProjs().add(GalacticFactory.spawnProjectile(projectileType, x + getWidth() / 2, y));
	}

}