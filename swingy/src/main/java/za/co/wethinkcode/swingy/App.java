package za.co.wethinkcode.swingy;

import za.co.wethinkcode.controllers.GameController;
import za.co.wethinkcode.database.HeroStorage;
import za.co.wethinkcode.models.Hero;

public class App 
{
    public static void main( String[] args )
    {
    	HeroStorage.connect();
    	
    	if (args.length != 1) {
    		System.out.println("Invalid argument list");
    		return;
    	}
    	
    	GameController controller = null;
    	
    	if (args[0].equals("gui")) {
    		controller = new GameController("gui");
    	} else if (args[0].equals("console")) {
    		controller = new GameController("console");
    	} else {
    		System.out.println("You must provide [gui] or [console] as an argument.");
    		return;
    	}
    	
    	while (controller.run()) {}
    	
    }
}
