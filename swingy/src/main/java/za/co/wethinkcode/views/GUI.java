package za.co.wethinkcode.views;

import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements Viewable {

	private JFrame mainFrame; 
	private JButton loadGame;
	private JButton newGame;
	private JMenuBar menu;
	private JPanel mainPanel;
	
	public GUI() {
		mainFrame = new JFrame("Swingy");
		mainFrame.setSize(1200, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);				
		
		menu = new JMenuBar();
		JMenu m1 = new JMenu("OPTIONS");
		JMenu m2 = new JMenu("HELP");
		menu.add(m1);
		menu.add(m2);
		JMenuItem m11 = new JMenuItem("Switch to Console");
		JMenuItem m12 = new JMenuItem("Quit Game");
		JMenuItem m21 = new JMenuItem("About Swingy");
		m1.add(m11);
		m1.add(m12);
		m2.add(m21);

		loadGame = new JButton("Load Game");
		newGame = new JButton("New Game");
		loadGame.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO implement load game
			}
		});
		
		loadGame.setBounds(400, 600, 100, 50);
		newGame.setBounds(700, 600, 100, 50);
		
		JLabel title = new JLabel("Welcome to Swingy");
		title.setFont(new Font("Monaco", Font.PLAIN, 20));
		title.setBounds(500, 300, 400, 100);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		mainPanel.add(loadGame);
		mainPanel.add(newGame);
		mainPanel.add(title);
		
		mainFrame.getContentPane().add(menu, BorderLayout.SOUTH);
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setVisible(true);
//		loadGame = new JButton("Load Game");
//		newGame = new JButton("New Game");
//		mainFrame.getContentPane().add(loadGame);
//		mainFrame.getContentPane().add(newGame);
	}
	
	@Override
	public void display(GameBoard g, String x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hero newHero() {
		// TODO Auto-generated method stub
		System.out.println("Displayed load game");
		return null;
	}

	@Override
	public inputType getMovementDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public inputType showEnemyEncounter(Villain v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showRun(boolean success) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showDeath(Villain v, Hero h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showWonFight(Villain v, Hero h) {
		// TODO Auto-generated method stub

	}

	@Override
	public inputType showArtifactDropped(Artifact a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showWonCurrentMap() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showLevelUp(Hero h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showHeroStats(Hero h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showGameQuit() {
		// TODO Auto-generated method stub

	}

}
