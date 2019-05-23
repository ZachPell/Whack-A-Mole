//NAMES: Jonathan Hassel and Zachary Pell
//Date: 5/3/17
//Class: cop3252 - Thrasher

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu extends JPanel
{	//member data for the start menu
	private JLabel title = new JLabel("Whack-A-Mole");
	private JLabel mole = new JLabel();
	private ImageIcon icon = new ImageIcon("play_button.png");
	private JLabel playButton = new JLabel(icon);
	private Ellipse2D.Double hole = new Ellipse2D.Double(75, 227, 450, 200);		//for painting a hole
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent( g );
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);		//hole color
		setBackground(new Color(0, 50, 0));		//background color

		//setting title label to specific position and font, then adding it to the start screen
		title.setForeground(Color.YELLOW);		//title text color
		title.setFont(new Font("Dialog", Font.BOLD, 60));
		title.setBounds(55, 50, 485, 50);
		add(title);

		playButton.setBounds(230, 530, 140, 100);		//positioning playButton on start screen
		add(playButton);		//adding playButton to start screen

		//turning mole into an icon and scaling it to the appropriate size
		mole.setIcon(new ImageIcon(new ImageIcon("mole.png").getImage().getScaledInstance(400, 280, Image.SCALE_DEFAULT)));
		mole.setBounds(100, 170, 400, 280);
		add(mole);			//adds mole to start screen
		g2d.fill(hole);		//paints a hole to start screen
	}

/*
 * 		GET FUNCTIONS FOR PRIVATE MEMBER DATA
 */
	
	public JLabel getPlayButton()
	{
		return playButton;
	}
	
}
