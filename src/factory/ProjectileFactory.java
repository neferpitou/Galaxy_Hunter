package factory;

import entities.Missile;
import entities.Projectile;

public class ProjectileFactory {

	public static Projectile spawnProjectile(ProjectileType p, int x, int y){
		Projectile proj = null;

		switch (p) {
		case MISSILE:
			proj = new Missile(x, y);
			break;

		default:
			// throw some exception
			break;
		}
		
		return proj;
	}

}
