package za.co.wethinkcode.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import za.co.wethinkcode.controllers.GuiController;
import za.co.wethinkcode.database.HeroStorage;
import za.co.wethinkcode.exceptions.RequiredToFightException;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.views.Viewable.inputType;

public class GUI {

	List<GuiController> observers = new ArrayList<GuiController>(20);
	GuiController observer = null;
	
	private ArrayList<Hero> savedHeroes;
	
	//Variables declaration - mainFrame
	private JFrame mainFrame; 
	private JButton loadGame;
	private JButton newGame;
	private JMenuBar menu;
	private JPanel mainPanel;
	//End
	
	// Variables declaration - loadGameFrame
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
	//End
	
    // Variables declaration - gameFrame
    private JFrame gameFrame;
    private javax.swing.JLabel combatCommandsLabel;
    private javax.swing.JPanel combatPanel;
    private javax.swing.JButton eastButton;
    private javax.swing.JButton fightButton;
    private javax.swing.JScrollPane gameLogPane;
    private javax.swing.JButton leaveItemButton;
    private javax.swing.JLabel logLabel;
    private javax.swing.JTextPane logTextPane;
    private javax.swing.JLabel movementCommandsLabel;
    private javax.swing.JPanel movementPanel;
    private javax.swing.JButton northButton;
    private javax.swing.JButton runButton;
    private javax.swing.JButton southButton;
    private javax.swing.JButton takeItemButton;
    private javax.swing.JButton westButton;
    // End of variables declaration
    
	public GUI() {
		initStartGameFrame();
	}
	
	public void register(GuiController c) {
		observers.add(c);
		observer = c;
	}

	private void initStartGameFrame() {
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
				if (name != null && heroClass != null && name.length() != 0 && heroClass.length() != 0) {
					for(int i = 0; i < observers.size(); i++) {
						observers.get(i).newHero(new Hero(name, heroClass));
					}
				}
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
	
	private void initLoadGameFrameComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();        
        heroList = new javax.swing.JList<String>();
        heroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        availableHeroesLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        heroDetailsPane = new javax.swing.JTextPane();
        heroDetailsLabel = new javax.swing.JLabel();
        selectHeroButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        loadGameFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        loadGameFrame.setResizable(false);
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
        
    	heroList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listSelectionActionPerformed();
			}
		});
    	
        loadGameFrame.pack();
    }
	
    private void selectHeroButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	
    	int selectedHeroIndex = heroList.getSelectedIndex();
    	
    	if (selectedHeroIndex == -1) {
    		return;
    	}
    	
    	//Notify observer that hero has been chosen
    	for (int i = 0; i < observers.size(); i++) {
    		observers.get(i).newHero(savedHeroes.get(selectedHeroIndex));
    	}
    	
    	//Close load game frame
    	loadGameFrame.dispatchEvent(
    			new WindowEvent(
    					loadGameFrame,
    					WindowEvent.WINDOW_CLOSING
    				)
    	);
    	
    	//Set up game frame
    	mainFrame.setVisible(false);
    	loadMainGameComponents();
    	gameFrame.setVisible(true);
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
    
    private void listSelectionActionPerformed() {
    	//Get details from hero storage for the index selected
    	int selectedIndex = heroList.getSelectedIndex();
    	
    	if (selectedIndex == -1) {
    		return;
    	}
    	
    	Hero selectedHero = savedHeroes.get(selectedIndex);
    	
    	//Display hero.toString to heroDetailsPane
    	heroDetailsPane.setText(selectedHero.toString());
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
    
    public void loadMainGameComponents() {

		gameFrame = new JFrame();
        gameLogPane = new javax.swing.JScrollPane();
        logTextPane = new javax.swing.JTextPane();
        logLabel = new javax.swing.JLabel();
        movementPanel = new javax.swing.JPanel();
        northButton = new javax.swing.JButton();
        movementCommandsLabel = new javax.swing.JLabel();
        southButton = new javax.swing.JButton();
        eastButton = new javax.swing.JButton();
        westButton = new javax.swing.JButton();
        combatPanel = new javax.swing.JPanel();
        fightButton = new javax.swing.JButton();
        combatCommandsLabel = new javax.swing.JLabel();
        runButton = new javax.swing.JButton();
        takeItemButton = new javax.swing.JButton();
        leaveItemButton = new javax.swing.JButton();

        gameFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        gameFrame.setResizable(false);
        
        gameLogPane.setViewportView(logTextPane);

        logLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        logLabel.setText("GAME LOG");

        northButton.setText("NORTH");
        northButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northButtonActionPerformed(evt);
            }
        });

        movementCommandsLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        movementCommandsLabel.setText("MOVEMENT COMMANDS");

        southButton.setText("SOUTH");
        southButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southButtonActionPerformed(evt);
            }
        });

        eastButton.setText("EAST");
        eastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastButtonActionPerformed(evt);
            }
        });

        westButton.setText("WEST");
        westButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout movementPanelLayout = new javax.swing.GroupLayout(movementPanel);
        movementPanel.setLayout(movementPanelLayout);
        movementPanelLayout.setHorizontalGroup(
            movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movementPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(movementCommandsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
            .addGroup(movementPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(northButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(southButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(westButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        movementPanelLayout.setVerticalGroup(
            movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movementCommandsLabel)
                .addGap(41, 41, 41)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(northButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(southButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(westButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        fightButton.setText("FIGHT");
        fightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fightButtonActionPerformed(evt);
            }
        });

        combatCommandsLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        combatCommandsLabel.setText("COMBAT COMMANDS");

        runButton.setText("RUN");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        takeItemButton.setText("TAKE ITEM");
        takeItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeItemButtonActionPerformed(evt);
            }
        });

        leaveItemButton.setText("LEAVE ITEM");
        leaveItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveItemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout combatPanelLayout = new javax.swing.GroupLayout(combatPanel);
        combatPanel.setLayout(combatPanelLayout);
        combatPanelLayout.setHorizontalGroup(
            combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(combatPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(takeItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leaveItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, combatPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(combatCommandsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        combatPanelLayout.setVerticalGroup(
            combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(combatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combatCommandsLabel)
                .addGap(41, 41, 41)
                .addGroup(combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(takeItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leaveItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(gameFrame.getContentPane());
        gameFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(movementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(combatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gameLogPane, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(logLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(movementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gameLogPane))
                .addGap(48, 48, 48))
        );

    	gameFrame.addWindowListener(new WindowAdapter() {
    	    public void windowClosing(WindowEvent e) {
    	    	mainFrame.setVisible(true);
    	    }
    	});
        
        gameFrame.pack();
    }                      

    private void northButtonActionPerformed(java.awt.event.ActionEvent evt) {
    		boolean moved = false;
    		
    		try {				
    			moved = observer.handleMovement(inputType.NORTH);
			} catch (RequiredToFightException e) {				
				appendToLog("You have encountered a villain:");
				appendToLog(e.getVillain().toString());
				return;
			} catch (IndexOutOfBoundsException e) {
				appendToLog("You have entered the void and are suddenly placed on a new map");
				notifyNewMap();
				return;
			} catch (Exception e) {
				//TODO: might adjust
				appendToLog("Something went wrong managing input");
				return;
			}
    		
    		if (moved == true) {
    			appendToLog("You have moved NORTH");
    		} else {
    			//Show pop up dialog that invalid input
    			JOptionPane.showMessageDialog(gameFrame, "You cannot perform this action now", "Invalid move", JOptionPane.ERROR_MESSAGE);
    		}
    }                                           

    private void notifyNewMap() {
    	observer.newMap();		
	}

	private void appendToLog(String x) {
    	StyledDocument doc = logTextPane.getStyledDocument();
    	
    	try {
    		doc.insertString(doc.getLength(), x + "\n", null);
    	} catch (Exception e) {
    		System.out.println("Error appending to logPane: " + x);
		}
    }
    
    private void southButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void eastButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void westButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void fightButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	boolean fight = false;
    	
    	try {
			observer.handleFight(inputType.FIGHT);
		} catch (Exception e) {
						
		}
    }                                           

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void takeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void leaveItemButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }
}
	/*
	
	private JFrame mainFrame; 
	private JButton loadGame;
	private JButton newGame;
	private JMenuBar menu;
	private JPanel mainPanel;
	private Hero chosenOne;
	private JFrame gameFrame;
	
	
	
    // Variables declaration - gameFrame                   
    private javax.swing.JLabel combatCommandsLabel;
    private javax.swing.JPanel combatPanel;
    private javax.swing.JButton eastButton;
    private javax.swing.JButton fightButton;
    private javax.swing.JScrollPane gameLogPane;
    private javax.swing.JButton leaveItemButton;
    private javax.swing.JLabel logLabel;
    private javax.swing.JTextPane logTextPane;
    private javax.swing.JLabel movementCommandsLabel;
    private javax.swing.JPanel movementPanel;
    private javax.swing.JButton northButton;
    private javax.swing.JButton runButton;
    private javax.swing.JButton southButton;
    private javax.swing.JButton takeItemButton;
    private javax.swing.JButton westButton;
    // End of variables declaration  
    
    private inputType movementDirection;
    private inputType fightOption;
    
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
		return movementDirection;
	}

	@Override
	public inputType showEnemyEncounter(Villain v) {
		logTextPane.setText("You have encountered a villain: \n" + v.toString());
		return fightOption;
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
    	
    	int selectedHeroIndex = heroList.getSelectedIndex();
    	
    	if (selectedHeroIndex == -1) {
    		return;
    	}
    	
    	chosenOne = savedHeroes.get(selectedHeroIndex);
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
    
    private void listSelectionActionPerformed() {
    	//Get details from hero storage for the index selected
    	int selectedIndex = heroList.getSelectedIndex();
    	
    	if (selectedIndex == -1) {
    		return;
    	}
    	
    	Hero selectedHero = savedHeroes.get(selectedIndex);
    	
    	//Display hero.toString to heroDetailsPane
    	heroDetailsPane.setText(selectedHero.toString());
    }
    
	private void initLoadGameFrameComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();        
        heroList = new javax.swing.JList<String>();
        heroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        availableHeroesLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        heroDetailsPane = new javax.swing.JTextPane();
        heroDetailsLabel = new javax.swing.JLabel();
        selectHeroButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        loadGameFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        loadGameFrame.setResizable(false);
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
        
    	heroList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listSelectionActionPerformed();
			}
		});
    	
        loadGameFrame.pack();
    }
	
	public void loadMainGameComponents() {

		gameFrame = new JFrame();
        gameLogPane = new javax.swing.JScrollPane();
        logTextPane = new javax.swing.JTextPane();
        logLabel = new javax.swing.JLabel();
        movementPanel = new javax.swing.JPanel();
        northButton = new javax.swing.JButton();
        movementCommandsLabel = new javax.swing.JLabel();
        southButton = new javax.swing.JButton();
        eastButton = new javax.swing.JButton();
        westButton = new javax.swing.JButton();
        combatPanel = new javax.swing.JPanel();
        fightButton = new javax.swing.JButton();
        combatCommandsLabel = new javax.swing.JLabel();
        runButton = new javax.swing.JButton();
        takeItemButton = new javax.swing.JButton();
        leaveItemButton = new javax.swing.JButton();

        gameFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        
        gameLogPane.setViewportView(logTextPane);

        logLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        logLabel.setText("GAME LOG");

        northButton.setText("NORTH");
        northButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northButtonActionPerformed(evt);
            }
        });

        movementCommandsLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        movementCommandsLabel.setText("MOVEMENT COMMANDS");

        southButton.setText("SOUTH");
        southButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southButtonActionPerformed(evt);
            }
        });

        eastButton.setText("EAST");
        eastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastButtonActionPerformed(evt);
            }
        });

        westButton.setText("WEST");
        westButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout movementPanelLayout = new javax.swing.GroupLayout(movementPanel);
        movementPanel.setLayout(movementPanelLayout);
        movementPanelLayout.setHorizontalGroup(
            movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movementPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(movementCommandsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
            .addGroup(movementPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(northButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(southButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(westButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        movementPanelLayout.setVerticalGroup(
            movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movementCommandsLabel)
                .addGap(41, 41, 41)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(northButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(southButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(westButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        fightButton.setText("FIGHT");
        fightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fightButtonActionPerformed(evt);
            }
        });

        combatCommandsLabel.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        combatCommandsLabel.setText("COMBAT COMMANDS");

        runButton.setText("RUN");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        takeItemButton.setText("TAKE ITEM");
        takeItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeItemButtonActionPerformed(evt);
            }
        });

        leaveItemButton.setText("LEAVE ITEM");
        leaveItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveItemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout combatPanelLayout = new javax.swing.GroupLayout(combatPanel);
        combatPanel.setLayout(combatPanelLayout);
        combatPanelLayout.setHorizontalGroup(
            combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(combatPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(takeItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leaveItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, combatPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(combatCommandsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        combatPanelLayout.setVerticalGroup(
            combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(combatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combatCommandsLabel)
                .addGap(41, 41, 41)
                .addGroup(combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(combatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(takeItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leaveItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(gameFrame.getContentPane());
        gameFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(movementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(combatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gameLogPane, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(logLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(movementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gameLogPane))
                .addGap(48, 48, 48))
        );

        gameFrame.pack();
        gameFrame.setVisible(true);
    }                      

    private void northButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    	movementDirection = inputType.NORTH;
    }                                           

    private void southButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void eastButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void westButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void fightButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    	fightOption = inputType.FIGHT;
    }                                           

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void takeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void leaveItemButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }

}*/
