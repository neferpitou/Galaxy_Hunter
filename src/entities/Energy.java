package entities;

import java.awt.Image;

import kernel.Kernel;
import factory.ProjectileType;

public class Energy extends Projectile {

		private static Image image = Kernel.getInstance().loadImage("energy.png");
		private static int SPEED = 10;
		private static int DAMAGE = 5;
		
		public Energy(int x, int y) {
			super(ProjectileType.ENERGY, image, SPEED, DAMAGE, x, y);
		}
}
