package za.co.wethinkcode.views;

import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;

public interface Viewable {
	
	public enum inputType {
		NORTH,
		SOUTH,
		EAST,
		WEST,
		FIGHT,
		RUN,
		TAKE_ITEM,
		LEAVE_ITEM,
		UNKNOWN
	};
	
	public void display(GameBoard g, String x);
	public Hero newHero();
	public inputType getMovementDirection();
	public inputType showEnemyEncounter(Villain v);
	public void showRun(boolean success);
	public void showDeath(Villain v, Hero h);
	public void showWonFight(Villain v, Hero h);
	public inputType showArtifactDropped(Artifact a);
	public void showWonCurrentMap();
}
