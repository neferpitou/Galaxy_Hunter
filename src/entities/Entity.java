package entities;

import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Entity {

	protected Image image;
	protected int speed;
	protected int damage;
	protected int x, y;
	private int width;
	protected int height;
	protected boolean isVisible;

	public Entity(Image img, int speed, int x, int y) {
		this.image = img;
		this.speed = speed;
		this.x = x;
		this.y = y;
		width = img.getWidth(null);
		height = img.getHeight(null);
		isVisible = true;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public boolean isVisible(){
		return isVisible;
	}
	
	public void setVisible(boolean visible){
		isVisible = visible;
	}
	
	public int getHeight() {
		return height;
	}
}
