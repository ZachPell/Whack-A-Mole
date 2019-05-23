//NAMES: Jonathan Hassel and Zachary Pell
//Date: 5/3/17
//Class: cop3252 - Thrasher

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGameScreen extends JPanel
{	//member data for end screen
	private JLabel gameOver = new JLabel("GAME OVER!!!");
	private JLabel finalScore;
	private JLabel newHighscore;
	private ImageIcon playAgainIcon = new ImageIcon("play_again_button.png");
	private JLabel playAgainButton = new JLabel(playAgainIcon);
	private ImageIcon exitIcon = new ImageIcon("quit_button.png");
	private JLabel exitButton = new JLabel(exitIcon);
	private static boolean isHighscore = false;		//to check if highscore was set

	public EndGameScreen()
	{	//JLabels for score or new highscore
		finalScore = new JLabel("YOUR SCORE: " + MainGame.getScore());
		newHighscore = new JLabel("NEW HIGHSCORE: " + MainGame.getScore());
		
		//mouse listener for playAgainButton
		playAgainButton.addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{	//removes end screen then starts a new game
				MainGame.getFrame().remove(MainGame.getEndScreen());
				MainGame.playGame();
				MainGame.getFrame().repaint();
			}
		});

		//mouse listener for exitButon
		exitButton.addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{	//exits the program
				System.exit(0);
			}
		});

		//if there is a new highscore
		if(MainGame.getScore() > MainGame.getHighscore())
		{		//declares a FileWriter
				FileWriter out = null;
         		try
         		{	//if there is a new highscore, set isHighscore to true for later if statement. Create FileWriter to write new highscore to
					EndGameScreen.isHighscore = true;
					out = new FileWriter("highscore");
					out.write(String.valueOf(MainGame.getScore()));
				}
         		catch(IOException e)
         		{	//for any exceptions in IO
         			e.printStackTrace();
         		}
         		finally
         		{	//closes file if is was open
         			try
         			{
						if(out != null)
							out.close();
              		}
         			catch(IOException e)
         			{
         				
         			}
         		}
          }
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(new Color(0, 50, 0));		//background color

		//sets game over text, color and position
		gameOver.setForeground(Color.YELLOW);
		gameOver.setFont(new Font("Dialog", Font.BOLD, 60));
		gameOver.setBounds(57, 30, 485, 70);
		add(gameOver);

		if(isHighscore)
		{	//if there is a new highscore, use newHighscore JLabel
			newHighscore.setForeground(Color.CYAN);
			newHighscore.setFont(new Font("Dialog", Font.BOLD, 40));
			newHighscore.setBounds(65, 200, 470, 70);
			add(newHighscore);
		}
		else
		{	//if there is not a new highscore, use finalScore JLabel
			finalScore.setForeground(Color.WHITE);
			finalScore.setFont(new Font("Dialog", Font.BOLD, 40));
			finalScore.setBounds(110, 200, 380, 70);
			add(finalScore);
		}

		//positioning and adding playAgainButton
		playAgainButton.setBounds(203, 350, 195, 90);
		add(playAgainButton);

		//positioning and adding exitButton
		exitButton.setBounds(267, 500, 65, 65);
		add(exitButton);


	}
	
/*
 * 		SET FUNCTIONS
 */
	
	public static void resetIsHighscore()
	{
		isHighscore = false;
	}
}
