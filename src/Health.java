import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Health extends JComponent implements Constants //health class that keeps track of how much life you have
{
	private int currentHealth;
	
	public Health() //constructor at beginning of game
	{
		currentHealth = maxHealth;
	}
	
	public int getCurrentHealth() //returns the current health
	{
		return currentHealth;
	}
	
	public void resetHealth() //resets health on new lives
	{
		currentHealth = maxHealth;
	}
	
	public void changeHealth(int s) //losing health by a certain amount
	{
		currentHealth = currentHealth + s;
	}
	
	public void drawHealthBar(Graphics g)
	{
		g.setColor(textCol); //draws white background of health
		g.fillRect(0, 0, maxHealth*5 + 20, 70);
		g.setFont(f);
		
		g.setColor(healthCol);
		g.fillRect(10, 10, currentHealth*5, 50); //draws rectangle (x, y, width, height)
		
		g.setColor(Color.BLACK); //puts "health" on the health bar
		g.drawString("Health: " + currentHealth, 10, 50);
	}
	
}
