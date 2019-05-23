//NAMES: Jonathan Hassel and Zachary Pell
//Date: 5/3/17
//Class: cop3252 - Thrasher

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class MainGame
{	//member data for different phases of the game
	private static JFrame frame = new JFrame("Whack-A-Mole");
	private static StartMenu menu = new StartMenu();
	private static GameBoard board = new GameBoard();
	private static EndGameScreen endScreen;
	private static Random rand = new Random();
	private static int score, highscore;

	public static void main(String args[])
	{	//for positioning JLabels at certain coordinates
		board.setLayout(null);

		frame.add(menu);			//adds menu to the frame

		menu.getPlayButton().addMouseListener(new MouseAdapter()
		{	//mouse listener for the play button. Starts game on mouse released
			public void mouseReleased(MouseEvent e)
			{	//removes menu and calls playGame function
				frame.remove(menu);
				playGame();
			}
		});

		//game windows size and default close operation
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 700);
		frame.setResizable(false);
		frame.setVisible(true);


/*	
 * Source for idea on how to change mouse cursor into an icon
 * http://stackoverflow.com/questions/4274606/how-to-change-cursor-icon-in-java	
*/	
		
		//to change the cursor to an icon
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("mallet.png");
		Point position = new Point(1,1);
		Cursor cursor = t.createCustomCursor(icon , position, "name");
		frame.setCursor(cursor);			//adds cursor to the frame
	}

	
	
	
	
	public static void playGame()
	{	//setting score, highscore, mole count to 0 and isHighscore boolean to false
		setScore(0);
		setHighscore(0);
		Mole.resetCount();
		EndGameScreen.resetIsHighscore();

		//clears the board for any moles(after a game has already been played)
		for(int i = 0; i < 16; ++i)
			board.getMoleArray()[i].setVisible(false);
		
		board.getScoreLabel().setText("Score: " + score);		//sets score label on game board

		try
		{
			Scanner in = new Scanner(new File("highscore"));		//to read in a highscore from highscore file

			//if there is an int: read it in, else close file
			if(in.hasNextInt())
				highscore = in.nextInt();
			in.close();

			board.getHighscoreLabel().setText("Highscore: " + highscore);		//sets the highscore label
																				//any int read in will be a highscore
			//adds board and updates interface
			frame.add(board);
			frame.validate();

			//loop where moles are randomly chosen to pop up
			chooseMole();
		}
		catch(IOException e)
		{	//for File IO errors
			e.printStackTrace();
		}
	}

	public static void chooseMole()
	{	//gets a random hole number and sets mole in that hole to visible
		int holeNum = rand.nextInt(16);

		board.getMoleArray()[holeNum].setVisible(true);
		board.getMoleArray()[holeNum].startTimer();
	}

	
/*
 * Source for the idea on how to play a sound file in java
 * http://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java	
 */
	
	public static void playSound(String fileName) 
	{	//plays a sound file that is passed in
		try
		{
			AudioInputStream in;
		    File file = new File(fileName);
		    in = AudioSystem.getAudioInputStream(file.toURI().toURL());  
		    Clip clip = AudioSystem.getClip();		    
		    clip.open(in);
		    clip.start();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}
		catch(UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
	}
	
	
/*
 * 		GET AND SET FUNCTIONS FOR PRIVATE MEMBER DATA
 */
	
	public static JFrame getFrame()
	{
		return frame;
	}
	
	public static GameBoard getBoard()
	{
		return board;
	}
	
	public static void createEndScreen()
	{
		endScreen = new EndGameScreen();
	}
	
	public static EndGameScreen getEndScreen()
	{
		return endScreen;
	}
	
	

	public static int getHighscore()
	{
		return highscore;
	}

	public static int getScore()
	{
		return score;
	}
	
	public static void setHighscore(int value)
	{
		highscore = value;
	}

	public static void setScore(int value)
	{
		score = value;
	}
	
	public static void incrementScore()
	{
		score++;
	}
}


