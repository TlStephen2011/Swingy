package za.co.wethinkcode.controllers;

import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
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
	
	public GuiController() {
		//Initialize GUI
		view = new GUI();
		view.register(this);
		makeNewBoard();
		allowAllMovement();
	}

	public boolean run() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void newHero(Hero h) {
		myHero = h;
		System.out.println(h.toString());
	}
	
	public boolean handleMovement(inputType in) {
		for (int i = 0; i < status.size(); i++) {
			if (in == status.get(i)) {
				//first check if required to fight
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
	
}
