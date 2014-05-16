package entities;

import java.awt.Image;

import factory.ProjectileType;
import kernel.Kernel;


public class Missile extends Projectile {

	protected static Image image = Kernel.getInstance().loadImage("missile.png");
	private static int SPEED = 10;
	private static int DAMAGE = 5;
	
	public Missile(int x, int y) {
		super(ProjectileType.MISSILE, image, SPEED, DAMAGE, x, y);
	}
}
