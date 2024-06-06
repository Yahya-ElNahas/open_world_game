package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gui.GamePanel;
import utils.Direction;

public class Entity {

	public int posX, posY, speed;
	protected BufferedImage[] entity_idle_image, entity_walking_image;
	private int imageIndex;
	private Direction directionV, directionH;
	private boolean isMoving;
	
	public Entity(int posX, int posY, int speed)
	{
		this.posX = posX;
		this.posY = posY;
		this.speed = speed;
		directionV = directionH = Direction.IDLE;
		entity_idle_image = new BufferedImage[4];
		entity_walking_image = new BufferedImage[4];
		imageIndex = 0;
		isMoving = false;
	}
	
	public void move(int up, int down, int left, int right)
	{
		if(up == 0 && down == 0 && left == 0 && right == 0) isMoving = false;
		else isMoving = true;
		if(up == 1) {
			directionV = Direction.UP;
			if(left == 0 && right == 0)
				directionH = Direction.IDLE;
		}
		if(down == 1) {
			directionV = Direction.DOWN;
			if(left == 0 && right == 0)
				directionH = Direction.IDLE;
		}
		if(left == 1) {
			directionH = Direction.LEFT;
			if(up == 0 && down == 0)
				directionV = Direction.IDLE;
		}
		if(right == 1) {
			directionH = Direction.RIGHT;
			if(up == 0 && down == 0)
				directionV = Direction.IDLE;
		}
		posY -= up*speed;
		posY += down*speed;
		posX -= left*speed;
		posX += right*speed;
	}
	
	public void incrementImageIndex()
	{
		imageIndex = imageIndex >= 3 ? 0 : imageIndex + 1;
	}
	
	public void draw(Graphics2D g)
	{
		draw(g, posX, posY);
	}
	
	protected void draw(Graphics2D g, int x, int y)
	{
		BufferedImage image = isMoving ? entity_walking_image[imageIndex] : entity_idle_image[imageIndex];
		float rotation = 0;
		if(directionV == Direction.UP && directionH == Direction.IDLE) {
			rotation = -90;
		}
		if(directionV == Direction.DOWN && directionH == Direction.IDLE) {
			rotation = 90;
		}
		else if(directionV == Direction.IDLE && directionH == Direction.LEFT) {
			rotation = 180;
		}
		else if(directionV == Direction.UP && directionH == Direction.LEFT) {
			rotation = 180 + 45/2;
		}
		else if(directionV == Direction.UP && directionH == Direction.RIGHT) {
			rotation = -45 / 2;
		}
		else if(directionV == Direction.DOWN && directionH == Direction.LEFT) {
			rotation = 180 - 45/2;
		}
		else if(directionV == Direction.DOWN && directionH == Direction.RIGHT) {
			rotation = 45 / 2;
		}
		g.rotate(Math.toRadians(rotation), x + GamePanel.TILE_SIZE / 2, y + GamePanel.TILE_SIZE / 2);
		g.drawImage(image, x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
	}
}










