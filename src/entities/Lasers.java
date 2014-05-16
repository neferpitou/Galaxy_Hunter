package entities;

import java.awt.Image;

import kernel.Kernel;
import factory.ProjectileType;

public class Lasers extends Projectile {

		private static Image image = Kernel.getInstance().loadImage("lasers.png");
		private static int SPEED = 10;
		private static int DAMAGE = 5;
		
		public Lasers(int x, int y) {
			super(ProjectileType.LASERS, image, SPEED, DAMAGE, x, y);
		}
}

