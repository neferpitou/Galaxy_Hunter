package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import factory.GalacticFactory;
import factory.ProjectileType;
import kernel.Kernel;

public class Cruiser extends Ship {

	final static int SPEED = 5;
	final static ProjectileType PROJ = ProjectileType.MISSILE;
	private ArrayList<Projectile> projs = new ArrayList<Projectile>(100);
	
	public Cruiser(int x, int y){
		super(Kernel.getInstance().loadImage("space_ship.png"), SPEED, x, y, PROJ);
	}
	
	public void move() {
		moveBy(0, SPEED);
	}
	
	public void fire( ) {
		int rand = (int) (Math.random() * 10);
		
		if (rand == 5 || rand == 7)
			projs.add(GalacticFactory.spawnEnemyProjectile(projectileType, x, y));
	}
	
	public void draw(Graphics g){
		if(isVisible())
			g.drawImage(image, x, y, null);
		
		for (int i = 0; i < projs.size(); i++) {
			if (projs.get(i).isVisible() == true) {
				projs.get(i).move(g);
			} else {
				projs.remove(i);
			}
		}
	}
	
	public ArrayList<Projectile> getEnemyProjs(){
		return projs;
	}
}
