package za.co.wethinkcode.controllers;

public class GameController {
	
	private ConsoleController console;
	private GuiController gui;
	
	
	public GameController(String view) {
		if (view.contentEquals("gui")) {
			gui = new GuiController();
		} else if (view.contentEquals("console")) {
			console = new ConsoleController();
		} else {
			System.out.println("Invalid view type");
			System.exit(1);
		}
	}
	
	public void switchView() {
		if (console == null) {
			gui = null;
			console = new ConsoleController();
		} else {
			gui = new GuiController();
			console = null;
		}
	}

	public boolean run() {
		if (console != null) {
			return console.run();
		} else {
			return gui.run();
		}
	}
	
}
