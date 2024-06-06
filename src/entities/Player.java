package entities;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.KeyHandler;

public class Player extends Entity{
	
	private KeyHandler keyHandler;
	public int screenX, screenY;
	
	public Player(int posX, int posY, int speed, KeyHandler keyHandler)
	{
		super(posX, posY, speed);
		this.keyHandler = keyHandler;
		initializeImage();
	}
	
	public void initializePlayerCamera(int screenX, int screenY)
	{
		this.screenX = screenX;
		this.screenY = screenY;
	}
	
	private void initializeImage()
	{
//		for(int i = 0;i < 2;i++) {
			try {
				entity_idle_image[0] = ImageIO.read(getClass().getResourceAsStream("/player_sprites/survivor-idle_knife_"+0+".png"));
				entity_idle_image[1] = ImageIO.read(getClass().getResourceAsStream("/player_sprites/survivor-idle_knife_"+5+".png"));
				entity_idle_image[2] = ImageIO.read(getClass().getResourceAsStream("/player_sprites/survivor-idle_knife_"+10+".png"));
				entity_idle_image[3] = ImageIO.read(getClass().getResourceAsStream("/player_sprites/survivor-idle_knife_"+5+".png"));
				
				entity_walking_image[0] = ImageIO.read(getClass().getResourceAsStream("/player_sprites/survivor-move_knife_"+0+".png"));
				entity_walking_image[1] = ImageIO.read(getClass().getResourceAsStream("/player_sprites/survivor-move_knife_"+5+".png"));
				entity_walking_image[2] = ImageIO.read(getClass().getResourceAsStream("/player_sprites/survivor-move_knife_"+10+".png"));
				entity_walking_image[3] = ImageIO.read(getClass().getResourceAsStream("/player_sprites/survivor-move_knife_"+5+".png"));
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
//		}
	}
	
	public void update()
	{
		int up = 0, down = 0, left = 0, right = 0;
		if(keyHandler.UP) up = 1;
		if(keyHandler.DOWN) down = 1;
		if(keyHandler.LEFT) left = 1;
		if(keyHandler.RIGHT) right = 1;
		move(up, down, left, right);
	}
	
	public void draw(Graphics2D g)
	{
		super.draw(g, screenX, screenY);
	}
}





