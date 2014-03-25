package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import factory.ProjectileFactory;
import factory.ProjectileType;
import kernel.Kernel;

public abstract class Ship {
	private int x;
	private int y;
	private BufferedImage ship;
	private Kernel kernel;
	private ArrayList<Projectile> projs = new ArrayList<Projectile>(100);
	
	public Ship(BufferedImage img, int x, int y){
		kernel = Kernel.getInstance();
		this.x = x;
		this.y = y;
		ship = img;
	}
	
	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public void fire( ProjectileType p ){
		projs.add(ProjectileFactory.spawnProjectile(p, x, y));
	}
	
	public void draw(Graphics g){
		g.drawImage(ship, x, y, null);
		
		for (int i = 0; i < projs.size(); i++){
			if (projs.get(i) != null){
				projs.get(i).move(g);
			}
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return ship.getWidth();
	}
	
	public double getHeight(){
		return ship.getHeight();
	}
}
