import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Life extends JComponent implements Constants
{
	private int currentLives;
	
	public Life()
	{
		currentLives = startLives;
	}
	
	public void resetLives() //resets lives to original total
	{
		currentLives = startLives;
	}
	public int getCurrentLives()// returns current amount of lives
	{
		return currentLives;
	}
	
	public void changeLives(int s) //changes how many lives you have 
	{
		currentLives = currentLives + s;
	}
	
	public void drawLives(Graphics g) //draws out all the lives in the upper right corner
	{
		int x = xBounds-110; //used to go from right to left
		
		//puts sign "Lives"
		Color testcol = new Color(254,0,254); //makes a new slightly different color so that can't get lives when touch lives
		g.setColor(testcol);
		g.setFont(new Font("Monospaced", Font.BOLD, 30));
		g.drawString("Lives", x, 30);
		
		//moves x over and draws ovals representing lives
		x= x - 25;
		for(int i = 1; i <= currentLives; i++)
		{
			
			g.fillOval(x, 10, 20, 20); // x, y, width, height
			x = x - 25;
		}
		
	}
}
