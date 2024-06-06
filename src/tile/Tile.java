package tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

enum TileType {
	GRASS, WATER, WALL
}

public class Tile {

	public BufferedImage image;
	public TileType type;
	
	public Tile(String path)
	{
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
//			this.type = type;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Tile()
	{
		
	}
}
