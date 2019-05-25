import java.awt.Color;
import java.awt.Font;

public interface Constants //class with all the needed constants for the classes
{
	public static final int xStart = 100; //top left x-coord of window
	public static final int yStart = 30;  //top left y-coord of window
	public static final int xBounds = 1750; //final window bounds x
	public static final int yBounds = 1000; //final window bounds y
	public static final int rockWidth = 100; //how big the rocks are
	public static final int rockHeight = 100;
	public static final Color backgroundColor = Color.BLACK; //color of the background of the game
	public static final int maxHealth = 100;
	public static final Font f = new Font("Monospaced", Font.PLAIN, 50); //creates new font to use for displaying texts
	public static final Font endScreen = new Font("Monospaced", Font.BOLD, 250); //the end game font to use
	public static final Color rockCol = Color.RED; //different colors of various objects
	public static final Color boxCol = Color.BLUE;
	public static final Color healthCol = Color.GREEN;
	public static final Color textCol = Color.WHITE;
	public static final Color lifeCol = Color.MAGENTA;
	public static final Color restartButCol = Color.CYAN;
	public static final Color quitButCol = Color.GRAY;
	public static final int startLives = 3;
	public static final int maxLives = 4;
}
