package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import factory.ProjectileFactory;
import factory.ProjectileType;

public abstract class Ship extends Entity {

	private ArrayList<Projectile> projs = new ArrayList<Projectile>(100);

	public Ship(Image img, int x, int y) {
		super(img, 0, x, y);
		this.x = x;
		this.y = y;
	}

	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public void fire(ProjectileType p) {
		getProjs().add(ProjectileFactory.spawnProjectile(p, x, y));
	}

	public void draw(Graphics g) {

		if(isVisible())
			g.drawImage(image, x, y, null);

		for (int i = 0; i < getProjs().size(); i++) {
			if (getProjs().get(i).isVisible() == true) {
				getProjs().get(i).move(g);
			} else {
				getProjs().remove(i);
			}
		}
	}

	public ArrayList<Projectile> getProjs() {
		return projs;
	}

	public void setProjs(ArrayList<Projectile> projs) {
		this.projs = projs;
	}
}
