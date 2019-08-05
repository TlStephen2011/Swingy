package za.co.wethinkcode.views;

import za.co.wethinkcode.database.HeroStorage;
import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

public class GUI implements Viewable {

	private ArrayList<Hero> savedHeroes;
	
	private JFrame mainFrame; 
	private JButton loadGame;
	private JButton newGame;
	private JMenuBar menu;
	private JPanel mainPanel;
	private Hero chosenOne;
	
	private JFrame loadGameFrame;
	private DefaultListModel<String> listModel; 
    private javax.swing.JLabel availableHeroesLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel heroDetailsLabel;
    private javax.swing.JTextPane heroDetailsPane;
    private javax.swing.JList<String> heroList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton selectHeroButton;
	
	public GUI() {
		mainFrame = new JFrame("Swingy"); 
		mainFrame.setSize(1200, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);				
		listModel = new DefaultListModel<String>();
		
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
				loadGameFrame = new JFrame();
				mainFrame.setVisible(false);
				initLoadGameFrameComponents();				
				savedHeroes = HeroStorage.getAllHeroes();								
				loadHeroesToList();
				loadGameFrame.setVisible(true);
			}
			
		});
		
		loadGame.setBounds(400, 600, 100, 50);
		newGame.setBounds(700, 600, 100, 50);
		
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter hero name: ");
				String heroClass = JOptionPane.showInputDialog("Enter hero class: ");
				if (name != null && heroClass != null && name.length() != 0 && heroClass.length() != 0)
					chosenOne = new Hero(name, heroClass);
				else {
					JOptionPane.showMessageDialog(mainFrame, "Inputs cannot be empty", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
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
	}
	
	private Hero chooseHero() {
		return new Hero("TyroneFromGUI", "GUI_Hero");
	}
	
	@Override
	public void display(GameBoard g, String x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hero newHero() {				
		return chosenOne;		
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
	
    private void selectHeroButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	loadGameFrame.dispatchEvent(
    			new WindowEvent(
    					loadGameFrame,
    					WindowEvent.WINDOW_CLOSING
    				)
    			);

    	
    	mainFrame.setVisible(true);
    }      
	
    private void loadHeroesToList() {    
		if (savedHeroes == null)
			return;
    	
    	int listSize = savedHeroes.size();
		
    	
    	if (listModel == null || listModel.size() != 0) {
			listModel = new DefaultListModel<String>();
		}
		
    	for (int i = 0; i < listSize; i++) {
    		listModel.addElement(savedHeroes.get(i).getHeroName());
		}
    	
    	heroList.setModel(listModel);
    }
    
	private void initLoadGameFrameComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();        
        heroList = new javax.swing.JList<String>();
        availableHeroesLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        heroDetailsPane = new javax.swing.JTextPane();
        heroDetailsLabel = new javax.swing.JLabel();
        selectHeroButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        loadGameFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(heroList);

        availableHeroesLabel.setText("Available Heroes");

        heroDetailsPane.setEditable(false);
        jScrollPane2.setViewportView(heroDetailsPane);

        heroDetailsLabel.setText("Hero Details");

        selectHeroButton.setText("Select Hero");
        selectHeroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectHeroButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(loadGameFrame.getContentPane());
        loadGameFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(availableHeroesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(heroDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(selectHeroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availableHeroesLabel)
                    .addComponent(heroDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectHeroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

    	loadGameFrame.addWindowListener(new WindowAdapter() {
    	    public void windowClosing(WindowEvent e) {
    	    	mainFrame.setVisible(true);
    	    }
    	});
        
        loadGameFrame.pack();
    }

}
