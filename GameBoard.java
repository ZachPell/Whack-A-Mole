//NAMES: Jonathan Hassel and Zachary Pell
//Date: 5/3/17
//Class: cop3252 - Thrasher

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JPanel
{	//member data for game board
	private Rectangle2D.Double rectangle;
	private Ellipse2D.Double hole;
	private Mole[] moleArray = new Mole[16];
	private JLabel scoreLabel = new JLabel("Score: " + 0);
	private JLabel highscoreLabel = new JLabel("Highscore: " + MainGame.getHighscore());


	public GameBoard()
	{	//creates and initializes a mole for each hole when game starts
		for(int i = 0; i < 16; ++i)
			moleArray[i] = new Mole();
		
		//every time the mouse is clicked, mallet noise plays
		addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{	
				MainGame.playSound("whack.wav");
			}
		});
	}
	
	public void paintComponent(Graphics g)
	{	
		super.paintComponent( g );
		setBackground(new Color(0, 50, 0));		//background color
		
		Graphics2D g2d = (Graphics2D) g;
		
		//color and dimensions for the inner rectangle
		g2d.setColor(new Color(20, 100, 0));
		double rx = .075 * getWidth();
		double ry = .15 * getHeight();
		double rw = .85 * getWidth();
		double rh = .80 * getHeight();
		
		//setting rectangle and adding it to the game board
		rectangle = new Rectangle2D.Double(rx, ry, rw, rh);
		g2d.fill(rectangle);
		
		//color and dimensions of each hole
		g2d.setColor(Color.BLACK);
		double hx = 53;
		double hy = 155;
		double hw = 117.3;
		double hh = 50.4;
		double temp = hx;
	
		//for loop to paint all the holes in the correct position, along with the moles
		for(int i = 0; i < 16; i++)
		{
			if((i % 4) == 0 && i != 0)
			{	//every four holes painted, change the y coordinate
				temp = hx;
				hy += .246 * rh;
			}
			
			//creating a new hole and mole and adding and positioning them on the game board
			hole = new Ellipse2D.Double(temp, hy, hw, hh);
			moleArray[i].setBounds((int) temp + 8, (int) hy - 12, (int) hw, (int) hh + 20);
			add(moleArray[i]);
			temp += .246 * rw;
			g2d.fill(hole);
			
		}
		

		JLabel title = new JLabel("Whack-A-Mole");		//game title for game board
		title.setForeground(Color.YELLOW);
		title.setFont(new Font("Dialog", Font.BOLD, 30));
		title.setBounds(180, 10, 240, 40);
		add(title);

		//setting color, size and position of the scoreLabel JLabel
		scoreLabel.setForeground(Color.RED);
		scoreLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		scoreLabel.setBounds(50, 90, 80, 12);
		add(scoreLabel);

		//setting color, size and position of the highscoreLabel JLabel
		highscoreLabel.setForeground(Color.CYAN);
		highscoreLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		highscoreLabel.setBounds(450, 90, 110, 15);
		add(highscoreLabel);		
	}
	
/*
 * 		GET FUNCTIONS FOR PRIVATE MEMBER DATA	
 */
	
	public Mole[] getMoleArray()
	{
		return moleArray;
	}
	
	public JLabel getScoreLabel()
	{
		return scoreLabel;
	}
	
	public JLabel getHighscoreLabel()
	{
		return highscoreLabel;
	}
}
