package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import factory.ProjectileType;

public abstract class Projectile extends Entity {

	private ProjectileType type = null;
	
	public Projectile(ProjectileType type, Image image, int speed, int dmg, int x, int y) {
		super(image, speed, x, y);
		this.type = type;
		damage = dmg;
	}
	
	public ProjectileType getType(){
		return type;
	}
	
	/**
	 * Once created, projectiles are moved until they are no longer
	 * seen, and then they are set invisible.
	 */
	/*
	 * Setting these objects as not visible signifies to the JVM
	 * that they should be collected
	 */
	public void move(Graphics g) {
		g.drawImage(image, x, y, null);
		y -= speed;
		isVisible = (y < 0) ? isVisible = false : true;
	}
}
