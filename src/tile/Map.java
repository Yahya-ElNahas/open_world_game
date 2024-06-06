package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import entities.Player;

public class Map {

	private Tile[][] map;
	private static Tile[] tiles;
	private int height, width, tileSize;
	
	public Map(int tileSize)
	{
		this.tileSize = tileSize;
		tiles = new Tile[10];
		initTiles();
		loadMap("/worlds/world1.txt");
	}
	
	private void loadMap(String path)
	{
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			LinkedList<int[]> tileList = new LinkedList<>();
			int i;
			for(i = 0;(line = br.readLine()) != null;i++) {
				int j;
				String[] a = line.split(" ");
				for(j = 0;j < a.length;j++) {
					int[] t = new int[2];
					t[0] = Integer.parseInt(a[j]);
					t[1] = j;
					tileList.add(t);
				}
				width = j;
			}
			height = i;
			map = new Tile[height][width];
			i = -1;
			int j = 0;
			while(!tileList.isEmpty()) {
				j = tileList.getFirst()[1];
				i = j == 0 ? i + 1 : i;
				map[i][j] = tiles[tileList.pop()[0]];
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void initTiles()
	{
		tiles[0] = new Tile("/tiles/water.png");
		tiles[1] = new Tile("/tiles/grass.png");
		tiles[2] = new Tile("/tiles/tree.png");
		tiles[3] = new Tile("/tiles/earth.png");
		tiles[4] = new Tile("/tiles/sand.png");
		tiles[5] = new Tile("/tiles/wall.png");
	}
	
	public void draw(Graphics2D g, Player player)
	{
		for(int i = 0;i < map.length;i++) {
			for(int j = 0;j < map[i].length;j++) {
				int wY = i * tileSize;
				int wX = j * tileSize;
				int sY = wY - player.posY + player.screenY;
				int sX = wX - player.posX + player.screenX;
				if(wX + tileSize > player.posX - player.screenX && wX - tileSize < player.posX + player.screenX &&
						wY + tileSize > player.posY - player.screenY && wY - tileSize < player.posY + player.screenY)
					g.drawImage(map[i][j].image, sX, sY, tileSize, tileSize, null);
			}
		}
	}
}





