package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import factory.ProjectileType;

public abstract class Projectile {
	
	protected BufferedImage image;
	protected int speed;
	protected int damage;
	protected int x, y;
	public boolean isVisible = false;
	private ProjectileType type = null;
	
	public Projectile(ProjectileType type, BufferedImage image, int speed, int dmg, int x, int y) {
		this.type = type;
		this.image = image;
		this.speed = speed;
		damage = dmg;
		this.x = x;
		this.y = y;
		
		isVisible = true;
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
