package entities;

import java.awt.image.BufferedImage;

import factory.ProjectileType;
import kernel.Kernel;


public class Missile extends Projectile {

	private static BufferedImage image = Kernel.getInstance().loadImage("space_ship.jpg");
	private static int SPEED = 10;
	private static int DAMAGE = 5;
	
	public Missile(int x, int y) {
		super(ProjectileType.MISSILE, image, SPEED, DAMAGE, x, y);
	}
}
