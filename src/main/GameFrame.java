package main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import kernel.Kernel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private Image galaxy_image;
	private int g_img_y, c_img_y;
	private final int img_height;

	public GameFrame() {
		setBackgroundImage("Messier_30.jpg");

		// Set the location for the first image at the bottom of the screen
		g_img_y = HEIGHT;
		img_height = galaxy_image.getHeight(null);

		// Set the location of the second image above the first image
		c_img_y = HEIGHT - img_height;
	}

	public void setBackgroundImage(String string) {
		galaxy_image = Kernel.getInstance().loadImage(string);
	}
	
	public void drawGameOver(final Graphics g){
		galaxy_image = Kernel.getInstance().loadImage("game_over.jpg");
		g.drawImage(galaxy_image, 0, Kernel.WIDTH / 2, null);
	}

	public void draw(final Graphics g) {

		// Scroll both backgrounds
		g_img_y++;
		c_img_y++;

		// Draw the background twice to the panel
		g.drawImage(galaxy_image, 0, g_img_y, null);
		g.drawImage(galaxy_image, 0, c_img_y, null);

		/*
		 * Calculate if one of the two background images are completely off
		 * the screen. If so, redraw the image on top of the currently
		 * visible image
		 */
		if (g_img_y > HEIGHT + img_height) {
			g_img_y = c_img_y - img_height;
		}

		if (c_img_y > HEIGHT + img_height) {
			c_img_y = g_img_y - img_height;
		}
	}
}