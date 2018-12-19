import java.awt.Graphics; //a bunch of things to import
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;


public class Game extends JPanel implements Constants, MouseListener, MouseMotionListener//need to have JPanel in order to use JPanel methods
{
	public static int score = 0; //score of the game (how many rocks hit ground)
	public static ArrayList<Rock> rockList; //makes the array of rocklist (has a bunch of rocks in it)
	public static ArrayList<Box> boxList; //array of boxes
	public static Life life = new Life();
	public static Health hp = new Health(); //new health object
	public static int mouX; //xLocation of mouse
	public static int mouY; //yLocation of mouse
	public static int curLvl = 1;
	public static ExtraLife exLife = new ExtraLife();
	public static int clickedMouX;
	public static int clickedMouY;
	
	public static boolean stillGoing = true;
	
	public boolean gameRunning()  //checks to see if your health is still above 0 (alive)
	{
		//System.out.println("Checking GAME");
		if(hp.getCurrentHealth() >= 0) //if hp > 0, youre still plying
		{
			//System.out.println("true");
			return true;
		}
		else if(hp.getCurrentHealth() < 0 && life.getCurrentLives() >= 1) //if health < 0 and you have 1 or more lives still
		{
			//System.out.println("true2");
			hp.resetHealth(); //resets health to 100
			life.changeLives(-1); //life counter goes down by one
			return true;
		} 
		else
		{
			//System.out.println("False");
			return false;
		}
	}
	
	public void incrementScore() //increments the score value by 1
	{
		score = score + 1;
	}
	
	public void paintComponent(Graphics g) //draws the game when asked to repaint or something like that
	{
		if(!this.gameRunning() && stillGoing) //checks if the game ended, but didn't quit yet
		{
			g.setColor(backgroundColor); //fills background
			g.fillRect(0, 0, xBounds, yBounds);
			
			//draws in the game over screen with score and level reached
			g.setFont(endScreen);
			g.setColor(Color.YELLOW);
			g.drawString("GAME OVER", xBounds/10, yBounds/2);
			g.setFont(f);
			g.drawString("Level " + curLvl, xBounds/10, 2*(yBounds/3));
			g.drawString("Score: " + score, xBounds/10, 2*(yBounds/3) +60);
			
			//draws the two buttons asking for replay or not
			g.setColor(restartButCol);
			g.fillRect(xBounds/2 - 50, 2*(yBounds/3) - 37, 200, 50); //x, y, width, height
			
			//writes in the reset text
			g.setColor(Color.BLACK);
			g.drawString("Replay", xBounds/2 - 50 + 10, 2*(yBounds/3));
			
			//draws the quit button
			g.setColor(quitButCol);
			g.fillRect(xBounds/2 + 300, 2*(yBounds/3) - 37, 200, 50);
			
			//draws the quit string
			g.setColor(Color.BLACK);
			g.drawString("Quit", xBounds/2 + 340, 2*(yBounds/3));
		}
		else
		{
			//draws background stuff
			g.setColor(backgroundColor);
			g.fillRect(0, 0, xBounds, yBounds); //(x,y,width,height)
			
			//draws the rocks using the arraylist
			for(Rock r: rockList)
			{
				r.draw(g);
			}
			for(Box b: boxList)
			{
				b.draw(g);
			}
		
			hp.drawHealthBar(g); //drawing the health bar on the screen
		
			
			//displaying the score on screen
			g.setColor(Color.WHITE); 
			g.setFont(f);
			g.drawString("Score: " + score, 0, yBounds - 55); //(string, xLoc, yLoc)
		
			//displays levels, life, and any extra life floating around
			this.displayLevel(g, curLvl);
			life.drawLives(g);
			exLife.drawExLife(g);
			// g.drawString("X: " + mouX + " Y: " + mouY, 0, yBounds-50); //prints out x and y location of the mouse
		}
	}
	
	public static void main(String[] args) throws InterruptedException, AWTException // throws exceptions so some of the mouse stuff works
	{
		
		rockList = new ArrayList<Rock>(); //declares the new arraylist
		boxList = new ArrayList<Box>();
		
		/*Default things that shouldn't be changed */
		JFrame gameWindow = new JFrame("Game Window"); //declares a new frame and calls it Game Window
		gameWindow.setBounds(xStart, yStart, xBounds, yBounds); //  (x coord, y coord, distance in x, distance in y) uses top left corner
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when x is clicked, it closes
		gameWindow.setVisible(true);//makes the window visible
		/* Don't change ^^^^^^ */
		
		Game rocksgame = new Game(); //declares new game object
		gameWindow.add(rocksgame); //adds the rocksgame to the window to display
		
		gameWindow.addMouseMotionListener(rocksgame); //adds the motion listener to the game
		gameWindow.addMouseListener(rocksgame); //adds the mouse listener to game (for things like mouse pressed and stuff)
		int numRocks = 1; //sets the number of rocks that will be falling at beginning
		
		for(int i = 1; i <= numRocks; i++) //makes all the rocks and puts them in the arraylist
		{
			rocksgame.addRock();
		}
		
		int numBoxes = 1;
		for(int k = 1; k <= numBoxes; k++) //adds stuff to the box array
		{
			rocksgame.addBox();
		}
		
		while(stillGoing) //if the person did not say to quit yet, it runs
		{
			rocksgame.repaint();
			TimeUnit.MILLISECONDS.sleep(10); //waits 10 milliseconds
			int timePassed = 0; //keeps track of how many times the loop has run
			
			while(rocksgame.gameRunning()) //loop that makes the rocks fall - only runs if game is still running
			{
				TimeUnit.MILLISECONDS.sleep(10); //waits 10 milliseconds
				timePassed++;
				if(timePassed == curLvl * 100) //after a certain amount of time, increments the level
				{
					rocksgame.addRock();
					rocksgame.addBox();
					curLvl++;
					timePassed=0;
				}
				
				for(Rock r : rockList) //goes through arraylist and makes the rocks fall
				{
					r.fall();
					r.checkAtEnd(rocksgame);
				}
			
				for(Box b : boxList)
				{
					b.move();
					b.checkAtSide(rocksgame);
				}
				exLife.moveX(); //moving the extra life
				exLife.moveY();
				rocksgame.repaint(); //'refreshes' the screen
				Color c = rocksgame.getMouCol(mouX,mouY); //gets color of the mouse at a point
				if (c.equals(rockCol) || c.equals(boxCol) || c.equals(textCol) || c.equals(healthCol)) //checks if the mouse is touching a blue,red, or white thing
				{
					hp.changeHealth(-1); //loses 1 point of health if touch
				}
			
				if(curLvl >= 20 && life.getCurrentLives() <= maxLives) //the extra life thing only starts at lvl 20
				{
					int rand = (int) (Math.random() * 30000);
					if (rand <= curLvl*2) //random chance that you get an extra life to appear (the higher the level, the more likely
					{
						exLife.resetCoords();
						exLife.setSpeeds();
						rocksgame.repaint();
					}
					if(c.equals(lifeCol)) //if touch an extra life, increments lives by 1 and makes life disappear
					{
						life.changeLives(1); //increments lives by 1
						exLife.disappearFromScreen();
						rocksgame.repaint(); //needs to repaint it immediately so that it doesn't add more than one life
					}
				}
			} //end of game while loop
			
			Color endGameC = rocksgame.getMouCol(clickedMouX,clickedMouY); //gets location of mouse click
			if(endGameC.equals(restartButCol)) //if hit reset, sets everything back up like normal
			{
				hp.resetHealth();
				life.resetLives();
				score = 0;
				curLvl = 1;
				for(int k = 0; k < rockList.size(); k++) //removes all the rocks and boxes from the array except the last onec+
				{
					rockList.remove(k);
					boxList.remove(k);
					if(!(rockList.size() == 1)) //if there is only one thing left, doesnt go down one
					{
						k--;
					}
				}
			}
			
			else if(endGameC.equals(quitButCol)) //if you hit the quit button, it exits the game
			{
				stillGoing = false;
				gameWindow.dispose(); //closes the window
			}
			clickedMouX = 0; //resets coordinates so it doesn't happen again immediately
			clickedMouY = 0;
		}
	}

	public Color getMouCol(int mx, int my) throws AWTException //method that gives the color that the mouse is on right now
	{
		Robot rb = new Robot(); //object used for determining color of a pixel 
		return rb.getPixelColor(mx, my);
	}
	
	public void addRock() //adds a rock to the rocklist array
	{
		int x = (int) (Math.random() * xBounds);
		int y = (int) (Math.random() * yBounds);
		int speed =(int) (Math.random() * 20) - 10; 
		if(curLvl >= 20)
		{
			speed = (int) (Math.random() * curLvl) - curLvl/2; //steadily there is more speed for lvl z>20
		}
		if (speed == 0) //ensures none stationary
		{
			speed = 5;
		}
		Rock a = new Rock(x,y,speed);
		rockList.add(a); //makes a new rock and adds it to array list
	}
	
	public void addBox() //method to add a box to the boxlist array
	{
		int x = (int) (Math.random() * xBounds);
		int y = (int) (Math.random() * yBounds);
		int speed = (int) (Math.random() * 20) - 10;
		if(curLvl >= 20)
		{
			speed = (int) (Math.random() * curLvl) - curLvl/2; //steadily there is more speed
		}
		if (speed == 0) // ensures that there is no box that is just stationary
		{
			speed = 5;
		}
		Box a = new Box(x,y,speed);
		boxList.add(a);
	}
	
	public void displayLevel(Graphics g, int lvl) //displays the current level
	{
		g.setColor(textCol);
		g.drawString("Level " + lvl, 0, yBounds-100);
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) { } //needed to add this method, it does nothing

	@Override
	public void mouseMoved(MouseEvent e) //gets the x and y location of the mouse constantly
	{
		mouX = e.getXOnScreen();
		mouY = e.getYOnScreen();
	}

	@Override
	public void mouseClicked(MouseEvent e)  //if mouse is clicked, gets those coordinates
	{
		clickedMouX = e.getXOnScreen();
		clickedMouY = e.getYOnScreen();
	}

	//extra methods added bc of interface used (they don't do anything)
	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) {	}

	@Override
	public void mousePressed(MouseEvent arg0) { }

	@Override
	public void mouseReleased(MouseEvent arg0) { }
}