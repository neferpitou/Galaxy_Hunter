package factory;

import kernel.Kernel;
import entities.Ship;

public class Cruiser extends Ship {

	final int SPEED = 5;
	
	public Cruiser(int x, int y){
		super(Kernel.getInstance().loadImage("space_ship.jpg"), x, y);
	}
	
	public void move(){
		moveBy(0, SPEED);
	}
}
