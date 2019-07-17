package za.co.wethinkcode.views;

import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;

public interface Viewable {
	
	public enum inputType {
		NORTH,
		SOUTH,
		EAST,
		WEST,
		FIGHT,
		RUN,
		TAKE_ITEM,
		UNKNOWN
	};
	
	public Viewable.inputType getInput();
	public void display(GameBoard g, String x);
	public Hero newHero();
	public Viewable.inputType showFightMenu(String villain);
	public void showWonFight();
	
}
