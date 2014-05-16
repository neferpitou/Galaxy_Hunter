package entities;

import java.awt.Graphics;

public class EnemyMissile extends Missile {

	public EnemyMissile(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void move(Graphics g) {
		g.drawImage(image, x, y, null);
		y += speed;
		isVisible = (y < 0) ? isVisible = false : true;
	}
}
