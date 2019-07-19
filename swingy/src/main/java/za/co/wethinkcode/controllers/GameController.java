package za.co.wethinkcode.controllers;

import java.util.ArrayList;

import za.co.wethinkcode.exceptions.*;
import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
import za.co.wethinkcode.utilities.Coordinates;
import za.co.wethinkcode.utilities.VillainBuilder;
import za.co.wethinkcode.views.Console;
import za.co.wethinkcode.views.Viewable;
import za.co.wethinkcode.views.Viewable.inputType;

public class GameController {
	
	private Hero hero;
	private ArrayList<Villain> villains;
	private GameBoard gameBoard;
	private Viewable view;
		
	public GameController() {
		this.view = new Console();
		this.hero = this.view.newHero();
		this.makeNewBoard();
		this.gameBoard.printBoard();
	}
	
	public boolean run() {
		boolean activeGame = true;

		while (activeGame) {
			inputType in = this.view.getMovementDirection();

			try {
				handleMovement(in);
			} catch (RequiredToFightException e) {
				Villain v = e.getVillain();
				inputType t = this.view.showEnemyEncounter(v);
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
			this.gameBoard.printBoard();
		}
		return activeGame;
	}	

	private void handleNewArtifact(inputType t, Artifact a) {
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
				this.hero.getXp(v.getLevel() * 100);
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
