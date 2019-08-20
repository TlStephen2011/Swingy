package za.co.wethinkcode.controllers;

import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.exceptions.ArtifactDroppedException;
import za.co.wethinkcode.exceptions.GameOverException;
import za.co.wethinkcode.exceptions.OccupiedByVillainException;
import za.co.wethinkcode.exceptions.RequiredToFightException;
import za.co.wethinkcode.models.Armor;
import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Helm;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
import za.co.wethinkcode.models.Weapon;
import za.co.wethinkcode.utilities.Coordinates;
import za.co.wethinkcode.utilities.VillainBuilder;
import za.co.wethinkcode.views.GUI;
import za.co.wethinkcode.views.Viewable.inputType;

public class GuiController {

	GUI view;
	Hero myHero;
	// Array of available moves that can be made
	List<inputType> status = new ArrayList<inputType>();
	GameBoard gameBoard;
	ArrayList<Villain> villains;
	inputType bufferedMove;
	Villain activeVillain = null;
	Artifact dropped = null;
	
	public GuiController() {
		//Initialize GUI
		view = new GUI();
		view.register(this);		
		allowAllMovement();
	}

	public boolean run() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void newHero(Hero h) {
		myHero = h;
		makeNewBoard();
		System.out.println(h.toString());
	}
	
	public boolean handleMovement(inputType in) throws RequiredToFightException {
		
		boolean isValid = checkValidMove(in); 
		
		if (!isValid) {
			return false;
		}

		Coordinates newCoords = new Coordinates(-1, -1); 
		Coordinates current = myHero.getPosition();
		
		if (in == inputType.NORTH) {
			newCoords = new Coordinates(current.getRow() - 1, current.getCol());
		} else if (in == inputType.SOUTH) {
			newCoords = new Coordinates(current.getRow() + 1, current.getCol());
		} else if (in == inputType.EAST) {
			newCoords = new Coordinates(current.getRow(), current.getCol() + 1);
		} else if (in == inputType.WEST) {
			newCoords = new Coordinates(current.getRow(), current.getCol() - 1);
		}
		
		try {
			gameBoard.move(current, newCoords);
		} catch (OccupiedByVillainException e) {
			
			bufferedMove = in;
			allowFightMovement();
			
			activeVillain = e.getVillain();			
			throw new RequiredToFightException(e.getVillain());
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		myHero.setPosition(newCoords);
		gameBoard.printBoard();
		System.out.println();
		return true;
	}
	
	private void allowFightMovement() {
		status = new ArrayList<inputType>();
		status.add(inputType.FIGHT);
		status.add(inputType.RUN);
	}
	
	private boolean checkValidMove(inputType in) {
		for (int i = 0; i < status.size(); i++) {
			if (in == status.get(i)) {
				return true;
			}
		}
		return false;
	}
	
	private void makeNewBoard() {
		int heroLevel = myHero.getLevel();
		gameBoard = new GameBoard((heroLevel - 1) * 5 + 10 - (heroLevel % 2));
		villains = VillainBuilder.buildVillains(myHero.getLevel(), this.gameBoard.getSize());
		myHero.setPosition(new Coordinates(this.gameBoard.getSize() / 2, this.gameBoard.getSize() / 2));
		
		try {
			this.gameBoard.place(myHero.getPosition(), myHero);
			for (int i = 0; i < this.villains.size(); i++) {
				this.gameBoard.place(this.villains.get(i).getPosition(), this.villains.get(i));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	private void allowAllMovement() {
		status = new ArrayList<inputType>();
		status.add(inputType.NORTH);
		status.add(inputType.EAST);
		status.add(inputType.SOUTH);
		status.add(inputType.WEST);
	}

	public boolean handleFight(inputType in) throws ArtifactDroppedException, GameOverException, RequiredToFightException {
		
		boolean valid = checkValidMove(in);
		
		if (!valid) {
			return false;
		}
		
		if (in == inputType.FIGHT) {
			
			if (myHero.attack(activeVillain) == true) {
				dropped = activeVillain.dropArtifact();
				
				//Level up
				int level = myHero.getLevel();				
				myHero.getXp(activeVillain.getLevel() * 100 + 100);
				
				if (dropped != null) {
					allowArtifactInteraction();
					throw new ArtifactDroppedException(dropped);
				}
				
				gameBoard.dropPosition(activeVillain.getPosition());
				allowAllMovement();
				try {
					handleMovement(bufferedMove);
				} catch (Exception e) {
					System.out.println("Wtf??");
					System.exit(1);
				}
				return true;
			} else {
				throw new GameOverException();
			}
			
		} else if (in == inputType.RUN) {
			
			boolean ranAway = myHero.run(activeVillain);
			
			if (ranAway == true) {
				allowAllMovement();
				bufferedMove = null;
				activeVillain = null;
				return true;
			} else {
				throw new RequiredToFightException(activeVillain);
			}
			
		}
		
		return false;
	}

	public void newMap() {
		makeNewBoard();
	}
	
	private void allowArtifactInteraction() {
		status = new ArrayList<inputType>();
		status.add(inputType.TAKE_ITEM);
		status.add(inputType.LEAVE_ITEM);		
	}

	public boolean handleTakeItem(inputType in) {
		
		boolean valid = checkValidMove(in);
		
		if (!valid) {
			return false;
		}
		
		if (dropped instanceof Weapon) {
			myHero.setWeapon((Weapon)dropped);
		} else if (dropped instanceof Armor) {
			myHero.setArmor((Armor)dropped);
		} else if (dropped instanceof Helm) {
			myHero.setHelm((Helm)dropped);
		}
		
		allowAllMovement();		
		
		return true;
	}

	public boolean handleLeaveItem(inputType leaveItem) {
		
		boolean valid = checkValidMove(inputType.LEAVE_ITEM);
		
		if (!valid) {
			return false;
		}
		
		allowAllMovement();
		return true;
	}
	
}
