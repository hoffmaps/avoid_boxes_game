import java.awt.Graphics;

public class ExtraLife implements Constants 
{
	private int xLoc;
	private int yLoc;
	private int speedX; //speeds in x and y direction
	private int speedY;
	
	public ExtraLife() //creates a new object with location away from screen
	{
		xLoc = xBounds + 10;
		yLoc = yBounds + 10;
		speedX = 0;
		speedY = 0;
	}
	
	public void changeyLoc(int s) //changes location of the point
	{
		yLoc = yLoc + s;
	}
	
	public void changexLoc(int s)
	{
		xLoc = xLoc + s;
	}
	
	public void moveY() //causes the life to move in Y direction
	{
		this.changeyLoc(speedY);
	}
	
	public void moveX() //move in x direction
	{
		this.changexLoc(speedX);
	}
	
	public void setSpeeds() //sets new random speeds for each coord
	{
		speedX = (int) (Math.random() * 10 ) + 1;
		speedY = 0;
	}
	
	public void disappearFromScreen() //when the mouse touches the extra life, it has to disappear from the screen
	{
		xLoc = xBounds + 10;
		yLoc = yBounds + 10;
		speedX = 0;
		speedY = 0;
	}
	public void resetCoords() //resets coordinates something random
	{
		xLoc = 0; //sets xLoc to a random location in xBounds
		yLoc = (int) (Math.random() * yBounds); //sets xLoc to a random location in xBounds
	}
	
	public void drawExLife(Graphics g) //draws the oval at a given location
	{
		g.setColor(lifeCol);
		g.fillOval(xLoc, yLoc, 50, 50);
	}
}
