import java.awt.Graphics; //a bunch of things to import
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Box extends JComponent implements Constants //extends rectange class in order to draw a rectange
{
		private int xLoc;
		private int yLoc;
		private int moveSpeed; //says how fast the boxes move
		
		public Box(int x, int y, int s)
		{
			xLoc = x;
			yLoc = y;
			moveSpeed = s;
		}
		
		public void draw(Graphics g) //draws the actual rock rectangle
		{
			g.setColor(boxCol);
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

		public void checkAtSide(Game m) //if the rock is at the bottom, moves it up to the top at a random x loc
		{
			if(xLoc > xBounds)
			{
				yLoc = (int) (Math.random() * yBounds); //sets yLoc to a random location in yBounds
				xLoc = 0;
				m.incrementScore(); //adds to the score of game m
			}
			else if (xLoc < -100)
			{
				yLoc = (int) (Math.random() * yBounds);
				xLoc = xBounds;
				m.incrementScore();
			}
		}
		
		public void move() //the method that causes them to fall down, based on their fall speed
		{
			this.changexLoc(moveSpeed);
		}
}
