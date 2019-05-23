//NAMES: Jonathan Hassel and Zachary Pell
//Date: 5/3/17
//Class: cop3252 - Thrasher

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mole extends JLabel
{	//time controls hole long mole is visible for, count for how many pop up
	private Timer timer;
	private static int count = 0;

	public Mole()
	{	//moles are not visible by default
		setVisible(false);
	
		//creating a mole icon and scaling it to fit the hole, then adding mouse listener to fire event when clicked
		setIcon(new ImageIcon(new ImageIcon("mole.png").getImage().getScaledInstance(100, 70, Image.SCALE_DEFAULT)));
		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{	//when mole is clicked, sound is played, score is incremented, score text is updated, mole goes away and board is repained
			    MainGame.playSound("mole.wav");		//mole noise
				MainGame.incrementScore();
				MainGame.getBoard().getScoreLabel().setText("Score: " + MainGame.getScore());
				setVisible(false);
				repaint();
			}
		});
	}

	public void startTimer()
	{	//if mole reaches timer without being hit, it goes invisible and a new mole is chosen
		ActionListener action = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	//checks to see if game is over
				if(count++ == 20)
				{	//stops the timer, removes the board, creates and adds the end screen and repaints
					timer.stop();
					MainGame.getFrame().remove(MainGame.getBoard());
					MainGame.createEndScreen();
					MainGame.getFrame().add(MainGame.getEndScreen());
					MainGame.getFrame().validate();
					return;
				}
				//stops timer, sets mole to not visible and chooses new mole for next pop-up
				timer.stop();	
				MainGame.chooseMole();
				setVisible(false);
			}
		};

		//implement a difficulty level? slider on start menu that determines speed
		timer = new Timer(1000, action);
		timer.setRepeats(false);
		timer.start();

	}

/*
 * 		GET AND SET FUNCTIONS
 */
	
	public static int getCount()
	{
		return count;
	}
	
	public static void resetCount()
	{
		count = 0;
	}
}


