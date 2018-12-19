import java.awt.Graphics; //a bunch of things to import
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Rock extends JComponent implements Constants //extends rectange class in order to draw a rectange
{
	private int xLoc;
	private int yLoc;
	private int fallSpeed; //says how fast the rocks fall
	
	public Rock(int x, int y, int s)
	{
		xLoc = x;
		yLoc = y;
		fallSpeed = s;
	}
	
	public void draw(Graphics g) //draws the actual rock rectangle
	{
		g.setColor(rockCol);
		g.fillRect(xLoc, yLoc, rockWidth, rockHeight);
	}

	public void changeyLoc(int s)
	{
		yLoc = yLoc + s;
	}
	
	public void changexLoc(int s)
	{
		xLoc = xLoc + s;
	}

	public void checkAtEnd(Game m) //if the rock is at the bottom, moves it up to the top at a random x loc
	{
		if(yLoc > yBounds) //checks if went off to the bottom
		{
			xLoc = (int) (Math.random() * xBounds); //sets xLoc to a random location in xBounds
			yLoc = 0;
			m.incrementScore(); //adds to the score of game m
		}
		else if(yLoc < -100) //chceks if it went off to the top
		{
			xLoc = (int) (Math.random() * xBounds);
			yLoc = yBounds;
			m.incrementScore();
		}
	}
	public void fall() //the method that causes them to fall down, based on their fall speed
	{
		this.changeyLoc(fallSpeed);
	}
}