package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import kernel.Kernel;

public class Ship {
	private int x;
	private int y;
	private BufferedImage ship;
	private Kernel kernel;
	
	public Ship(int x, int y){
		kernel = Kernel.getInstance();
		this.x = x;
		this.y = y;
		ship = Kernel.getInstance().loadImage("space_ship.jpg");
	}
	
	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics g){
		g.drawImage(ship, x, y, null);
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
