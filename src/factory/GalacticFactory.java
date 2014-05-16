package factory;

import entities.Cruiser;
import entities.EnemyMissile;
import entities.Energy;
import entities.Knight;
import entities.Lasers;
import entities.Missile;
import entities.Projectile;
import entities.Ship;
import entities.Talon;
import entities.Vixen;

public class GalacticFactory {
	
	public static Projectile spawnProjectile(ProjectileType p, int x, int y) {
		Projectile proj = null;

		switch (p) {
		case MISSILE:
			proj = new Missile(x, y);
			break;
		case LASERS:
			proj = new Lasers(x, y);
			break;
		case ENERGY:
			proj = new Energy(x, y);
			break;

		default:
			// throw some exception
			break;
		}

		return proj;
	}
	
	public static Ship spawnShip(ShipType a, int x, int y) {
		Ship entity = null;

		switch (a) {
		case CRUISER:
			entity = new Cruiser(x, y);
			break;

		default:
			// throw some exception
			break;
		}

		return entity;
	}

	public static Ship spawnShip(PlayerShipType a, int x, int y) {
		String shipIcon = "";
		int SPEED = 0;
		Ship p = null;

		switch (a) {
		case TALON:
			shipIcon = "talon.png";
			SPEED = 5;
			p = new Talon(shipIcon, x, y, SPEED);
			break;
		case VIXEN:
			shipIcon = "vixen.png";
			SPEED = 2;
			p = new Vixen(shipIcon, x, y, SPEED);
			break;
		case KNIGHT:
			shipIcon = "knight.png";
			SPEED = 7;
			p = new Knight(shipIcon, x, y, SPEED);
			break;
		default:
			shipIcon = "talon.png";
			SPEED = 5;
			p = new Talon(shipIcon, x, y, SPEED);
			break;
		}

		return p;
	}

	public static Projectile spawnEnemyProjectile(
			ProjectileType projectileType, int x, int y) {
		// TODO Auto-generated method stub
		return new EnemyMissile(x,y);
	}
}
