package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import controller.KeyHandler;
import entities.Player;
import tile.Map;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

	public static final int TILE_SIZE = 48;
	private static final int SCREEN_HEIGHT = 14 * TILE_SIZE;
	private static final int SCREEN_WIDTH = 24 * TILE_SIZE;
	private Thread GAME_THREAD;
	private KeyHandler keyHandler;
	private Player player;
	private Map map;
	private int FPS;
	
	public GamePanel()
	{
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		
		keyHandler = new KeyHandler();
		addKeyListener(keyHandler);
		setFocusable(true);
		player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 5, keyHandler);
		player.initializePlayerCamera(SCREEN_WIDTH/2 - TILE_SIZE/2, SCREEN_HEIGHT/2 - TILE_SIZE/2);
		map = new Map(TILE_SIZE);
		FPS = 60;
		startGameThread();
	}
	
	public void startGameThread()
	{
		GAME_THREAD = new Thread(this);
		GAME_THREAD.start();
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		double interval = 1_000_000_000 / FPS;
		double nextInterval = System.nanoTime() + interval;
		long currentTime;
		long lastTime = System.nanoTime();
		long timer = 0;
		int drawCount = 0;
		while(GAME_THREAD != null) {
			currentTime = System.nanoTime();
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			update();
			repaint();
			
			try {
				double remainingTime = nextInterval - System.nanoTime();
				remainingTime /= 1000000;
				remainingTime = remainingTime < 0 ? 0: remainingTime;
				timer++;
				Thread.sleep((long)remainingTime);
				drawCount++;
				nextInterval += interval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(drawCount % 20 == 0)
				player.incrementImageIndex();

			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update()
	{
		player.update();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		map.draw(g2, player);
		player.draw(g2);
		g2.dispose();
	}
}






