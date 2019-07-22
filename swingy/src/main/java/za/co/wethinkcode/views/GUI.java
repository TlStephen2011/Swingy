package za.co.wethinkcode.views;

import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;

public class GUI implements Viewable {

	@Override
	public void display(GameBoard g, String x) {
		// TODO Auto-generated method stub

	}

	@Override
	public Hero newHero() {
		// TODO Auto-generated method stub
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
