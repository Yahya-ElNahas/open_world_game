package gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	public GameFrame()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 700);
		setTitle("The Game");
		setResizable(false);
		setLocationRelativeTo(null);
		add(new GamePanel());
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new GameFrame();
	}
}
