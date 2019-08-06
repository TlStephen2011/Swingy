package za.co.wethinkcode.controllers;

import java.util.ArrayList;
import za.co.wethinkcode.views.*;
import za.co.wethinkcode.views.Viewable.inputType;
import za.co.wethinkcode.models.*;
import za.co.wethinkcode.exceptions.*;
import za.co.wethinkcode.utilities.*;

public class GameController {
	
	private Hero hero;
	private ArrayList<Villain> villains;
	private GameBoard gameBoard;
	private Viewable view;
		
	public GameController() {
		this.view = new GUI(); 
		while (this.hero == null) {
			this.hero = this.view.newHero();
			if (this.hero == null) {
				System.out.println("Hero is not yet ready");
			}
		}
		System.out.println(this.hero.toString());
		
		this.makeNewBoard();
		this.gameBoard.printBoard();
	}
	
	public boolean run() {
		boolean activeGame = true;

		if (this.view instanceof GUI) {
			((GUI) this.view).loadMainGameComponents();
		}
		
		while (activeGame) {
			inputType in = null;
			
			while (in == null) {
				in = this.view.getMovementDirection();
				System.out.println("Waiting for movement direction");
			}

			try {
				handleMovement(in);
			} catch (RequiredToFightException e) {
				Villain v = e.getVillain();
				inputType t = null;
				while (t == null) {
					this.view.showEnemyEncounter(v);					
				}
				
				Artifact a = null;

				try {
					a = handleEncounter(t, v, in);
				} catch (GameOverException gameOver) {
					this.view.showDeath(v, this.hero);
					activeGame = false;
				}

				if (a != null) {
					t = this.view.showArtifactDropped(a);
					handleNewArtifact(t, a);
				}
			} catch (IndexOutOfBoundsException e) {
				this.view.showWonCurrentMap();
				this.makeNewBoard();				
			} catch (Exception e) {
				System.out.println(e.getMessage());				
				System.exit(1);
			}
			
			if (activeGame)
				this.gameBoard.printBoard();
		}
		return activeGame;
	}	

	private void handleNewArtifact(inputType t, Artifact a) {
		if (t == inputType.TAKE_ITEM) {			
			if (a instanceof Weapon) {
				this.hero.setWeapon((Weapon)a);
			} else if (a instanceof Armor) {
				this.hero.setArmor((Armor)a);
			} else if (a instanceof Helm) {
				this.hero.setHelm((Helm)a);
			}
			this.view.showHeroStats(this.hero);
		}
	}

	private Artifact handleEncounter(inputType t, Villain v, inputType in) throws GameOverException {
		
		if (t == inputType.FIGHT) {
			if (this.hero.attack(v) == true) {
				this.view.showWonFight(v, this.hero);
				Artifact a = v.dropArtifact();
				
				this.gameBoard.dropPosition(v.getPosition());
				try {
					this.handleMovement(in);
				} catch (Exception e) {
					System.out.println("Villain still in place: " + e.getMessage());
					System.exit(1);
				}
				
				int level = this.hero.getLevel();				
				this.hero.getXp(v.getLevel() * 100 + 100);
				if (this.hero.getLevel() != level) {
					this.view.showLevelUp(this.hero);
				}
				return a;
			} else {
				throw new GameOverException();
			}
		} else {
			boolean ranAway = this.hero.run(v);
			this.view.showRun(ranAway);
			
			if (!ranAway) {
				return this.handleEncounter(inputType.FIGHT, v, in);
			}
		}
		
		return null;
	}

	private void handleMovement(inputType input) throws RequiredToFightException, IndexOutOfBoundsException {
		Coordinates co = this.hero.getPosition();
		Coordinates newCoordinates = null;

		if (input == inputType.QUIT) {
			//TODO: save state to FILE and exit gracefully
			this.view.showGameQuit();
			System.exit(0);
		}
		
		try {
			if (input == inputType.NORTH) {
				newCoordinates = new Coordinates(co.getRow() - 1, co.getCol());
			} else if (input == inputType.SOUTH) {
				newCoordinates = new Coordinates(co.getRow() + 1, co.getCol());
			} else if (input == inputType.EAST) {
				newCoordinates = new Coordinates(co.getRow(), co.getCol() + 1);
			} else if (input == inputType.WEST) {
				newCoordinates = new Coordinates(co.getRow(), co.getCol() - 1);
			}

			this.gameBoard.move(co, newCoordinates);
		} catch (OccupiedByVillainException e) {
			throw new RequiredToFightException(e.getVillain());
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		this.hero.move(newCoordinates);
	}
	
	private void makeNewBoard() {
		int heroLevel = this.hero.getLevel();
		this.gameBoard = new GameBoard((heroLevel - 1) * 5 + 10 - (heroLevel % 2));
		this.villains = VillainBuilder.buildVillains(this.hero.getLevel(), this.gameBoard.getSize());
		this.hero.setPosition(new Coordinates(this.gameBoard.getSize() / 2, this.gameBoard.getSize() / 2));
		
		try {
			this.gameBoard.place(this.hero.getPosition(), this.hero);
			for (int i = 0; i < this.villains.size(); i++) {
				this.gameBoard.place(this.villains.get(i).getPosition(), this.villains.get(i));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

}
